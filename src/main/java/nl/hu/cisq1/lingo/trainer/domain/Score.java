package nl.hu.cisq1.lingo.trainer.domain;

public class Score {
    private int Id;
    private int Result;

    public Score(int id, int result) {
        Id = id;
        Result = result;
    }

    public int getId() {
        return Id;
    }

    public int getResult() {
        return Result;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setResult(int result) {
        Result = result;
    }
}
