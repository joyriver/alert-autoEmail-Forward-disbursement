package com.kbers.alert;

/**
 * <a href="mailto:joyriver7@gmail.com">Jinxi Hong</a> 2017/1/6 10:14
 */
public class Exceeding {

    public boolean SummaryOdrExceeding(String odrs){
        if(odrs.matches(Constants.DOUBLE_REGEX)) {
            double odr = Double.parseDouble(odrs);
            if(odr >= 0.01 )
                return true;
        }
        return false;
    }

    public boolean SummaryOdrNormal(String odrs){
        if(odrs.matches(Constants.DOUBLE_REGEX)){
            double odr = Double.parseDouble(odrs);
            if(odr < 0.008)
                return true;
        }
        return false;
    }

    public boolean OdrNotice(String odrShort){
        if(odrShort.matches(Constants.DOUBLE_REGEX)) {
            double odr = Double.parseDouble(odrShort);
            if(odr >= 0.008 && odr <0.01)
                return true;
        }
        return false;
    }

    public boolean SummaryVTRExceeding(String validTrackingRate7days){
        if(validTrackingRate7days.matches(Constants.DOUBLE_REGEX)) {
            double vdr = Double.parseDouble(validTrackingRate7days);
            if(vdr <= 0.7)
                return true;
        }
        return false;
    }

    public boolean SummaryVTRNormal(String validTrackingRate7days){
        if(validTrackingRate7days.matches(Constants.DOUBLE_REGEX)) {
            double vdr =Double.parseDouble(validTrackingRate7days);
            if(vdr > 0.92)
                return true;
        }
        return false;
    }

    public boolean VTRNotice(String validTrackingRate7days){
        if(validTrackingRate7days.matches(Constants.DOUBLE_REGEX)) {
            double  vtr = Double.parseDouble(validTrackingRate7days);
            if( vtr <= 0.92 && vtr >= 0.7  )
                return true;
        }
        return false;
    }

    public boolean FeedbackNotice(String feedback30days, String orders30days){
        if(feedback30days.matches(Constants.INTEGER_REGX) && orders30days.matches(Constants.INTEGER_REGX))
        {
            double feedback = Integer.parseInt(feedback30days);
            double orders   = Integer.parseInt(orders30days);
            if(orders !=0)
            {
                double feedbackRate = feedback / orders;
                if(feedbackRate <= 0.12 && feedbackRate >= 0.1)
                    return true;
            }
        }
        return false;
    }


    public boolean SummaryFeedbackExceeding(String feedback30days, String orders30days){
        if(feedback30days.matches(Constants.INTEGER_REGX) && orders30days.matches(Constants.INTEGER_REGX))
        {
            double feedback = Integer.parseInt(feedback30days);
            double orders   = Integer.parseInt(orders30days);
            if(orders !=0)
            {
                double feedbackRate = feedback / orders;
                if(feedbackRate <= 0.1)
                    return true;
            }
        }
        return false;
    }

    public boolean SummaryFeedbackNormal(String feedback30days, String orders30days){
        if(feedback30days.matches(Constants.INTEGER_REGX) && orders30days.matches(Constants.INTEGER_REGX))
        {
            double feedback = Integer.parseInt(feedback30days);
            double orders   = Integer.parseInt(orders30days);
            if(orders !=0)
            {
                double feedbackRate = feedback / orders;
                if(feedbackRate > 0.12)
                    return true;
            }
        }
        return false;
    }


    public boolean SummaryLateShipmentRateExceeding(String lateShipmentRate7days){
        if(lateShipmentRate7days.matches(Constants.DOUBLE_REGEX)){
            double lateShipmentRate = Double.parseDouble(lateShipmentRate7days);
            if(lateShipmentRate >= 0.04 )
                return true;
        }
        return false;
    }

    public boolean SummaryLateShipmentRateNormal(String lateShipmentRate7days){
        if(lateShipmentRate7days.matches(Constants.DOUBLE_REGEX)){
            double lateShipmentRate = Double.parseDouble(lateShipmentRate7days);
            if(lateShipmentRate < 0.03 )
                return true;
        }
        return false;
    }


    public boolean LateShipmentRateNotice(String lateShipmentRate7days){
        if(lateShipmentRate7days.matches(Constants.DOUBLE_REGEX)){
            double lateShipmentRate = Double.parseDouble(lateShipmentRate7days);
            if(lateShipmentRate <= 0.04 && lateShipmentRate >= 0.03)
                return true;
        }
        return false;
    }


    public boolean SummaryRefundRateExceeding(String refundRate7days){
        if(refundRate7days.matches(Constants.DOUBLE_REGEX)){
            double refundRate = Double.parseDouble(refundRate7days);
            if(refundRate > 0.1)
                return true;
        }
        return false;
    }

    public boolean SummaryRefundRateNormal(String refundRate7days){
        if(refundRate7days.matches(Constants.DOUBLE_REGEX)){
            double refundRate = Double.parseDouble(refundRate7days);
            if(refundRate < 0.09)
                return true;
        }
        return false;
    }


    public boolean RefundRateNotice(String refundRate7days){
        if(refundRate7days.matches(Constants.DOUBLE_REGEX)){
            double refundRate = Double.parseDouble(refundRate7days);
            if(refundRate >= 0.09 && refundRate <= 0.1)
                return true;
        }
        return false;
    }


    public boolean SummaryPreFulfillCancelRateExceeding(String preFulfillCancelRate7days){
        if(preFulfillCancelRate7days.matches(Constants.DOUBLE_REGEX)){
            double preFulfillCancelRate = Double.parseDouble(preFulfillCancelRate7days);
            if(preFulfillCancelRate > 0.025 )
                return true;
        }
        return false;
    }

    public boolean SummaryPreFulfilledCancelRateNormal(String preFulfillCancelRate7days){
        if(preFulfillCancelRate7days.matches(Constants.DOUBLE_REGEX)){
            double preFulfillCancelRate = Double.parseDouble(preFulfillCancelRate7days);
            if(preFulfillCancelRate < 0.015 )
                return true;
        }
        return false;
    }


    public boolean PreFulfillCancelRateNotice(String preFulfillCancelRate7days){
        if(preFulfillCancelRate7days.matches(Constants.DOUBLE_REGEX)){
            double preFulfillCancelRate = Double.parseDouble(preFulfillCancelRate7days);
            if(preFulfillCancelRate <= 0.025 && preFulfillCancelRate >= 0.015)
                return true;
        }
        return false;
    }

}
