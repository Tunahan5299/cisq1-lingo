package nl.hu.cisq1.lingo.trainer.domain.round;

import nl.hu.cisq1.lingo.trainer.domain.words.Word;

import java.util.Calendar;
import java.util.Date;

public class Turn {
    private int id;
    private Word guessedWord;
    private Feedback round;
    private TurnState turnState;
    private Calendar startTime;
    private Calendar endTime;

    public Turn() {
    }

    public Turn(Calendar startTime, Calendar endTime) {
        this.startTime = startTime;
        this.endTime = addSeconds(startTime.getTime(), 30);
    }

    public boolean timeExceeded(){
        Calendar dateTime = Calendar.getInstance();
        if(endTime.getTime().compareTo(dateTime.getTime()) < 0){
            turnState = TurnState.INVALID;
            return true;
        }
        else {
            return false;
        }
    }

    public static Calendar addSeconds(Date date, Integer seconds) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.SECOND, seconds);
        return cal;
    }
}
