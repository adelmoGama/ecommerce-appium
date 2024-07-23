package ecommerceAppium;

public class AppiumUtils {

    public static Double getFormattedAmount(String amount) {
        return Double.parseDouble(amount.substring(1));
    }
}
