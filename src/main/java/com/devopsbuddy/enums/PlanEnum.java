package com.devopsbuddy.enums;

/**
 * Created by Carren.Dsouza on 20/04/2017.
 */
public enum PlanEnum {

    BASIC(1,"BASIC_PLAN"),
    PRO(2,"PRO_PLAN");

    private final  int id;
    private final String  planName;

   PlanEnum(int id,String planName){
       this.id =id;
       this.planName = planName;

   }

    public int getId() {
        return id;
    }


    public String getPlanName() {
        return planName;
    }
}
