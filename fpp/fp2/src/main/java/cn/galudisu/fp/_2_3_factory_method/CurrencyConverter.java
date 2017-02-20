package cn.galudisu.fp._2_3_factory_method;

/**
 * Java实现的工厂方法
 *
 * @author galudisu
 */
public class CurrencyConverter {
    private static final String YEN = "Yen";
    private static final String EURO = "Euro";
    private static final String DOLLAR = "Dollar";

    private static final class EuroToRupee implements Currency {
        @Override
        public String getConversionRateToIndianRupee() {
            return "82";
        }
    }

    private static final class DollarToRupee implements Currency {
        @Override
        public String getConversionRateToIndianRupee() {
            return "60";
        }
    }

    private static Currency createCurrencyFor(final String currencyStr) {
        if (currencyStr.equals(DOLLAR)) {
            return new DollarToRupee();
        }
        if (currencyStr.equals(EURO)) {
            return new EuroToRupee();
        }
        throw new IllegalArgumentException("Oops! no idea about <" + currencyStr + ">");
    }

    public static void main(String[] args) {
        System.out.println(createCurrencyFor(DOLLAR).getConversionRateToIndianRupee());
        System.out.println(createCurrencyFor(EURO).getConversionRateToIndianRupee());
        System.out.println(createCurrencyFor(YEN).getConversionRateToIndianRupee());
    }
}
