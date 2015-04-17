package com.test.geoquez;

/**
 * Created by mmxb on 2015/3/28.
 */
public class TrueFalse {
    private int questionId;
    private Boolean isTrue;


    /**
     * 初始化问题的ID和真假
     */
    public TrueFalse(int questionId,Boolean isTrue){
        this.questionId = questionId;
        this.isTrue = isTrue;
    }


    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public Boolean getIsTrue() {
        return isTrue;
    }

    public void setIsTrue(Boolean isTrue) {
        this.isTrue = isTrue;
    }

}
