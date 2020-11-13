
package com.classes.tip;

import java.util.ArrayList;
import java.util.List;

public class CountriesArray {

    private Country[] countries = new Country[]{
            new Country("Albania", "L", 0, 0.1, 0, 0.05,false
            ), new Country("Andorra", "€", 0, 0.1, 0.07, 0.13,true
    ), new Country("Armenia", "֏", 0.05, 0.2, 0, 0.05,false
    ), new Country("Austria", "€", 0, 0.1, 0, 0.1,true
    ), new Country("Azerbaijan", "₼", 0.07, 0.12, 0, 0.05,false
    ), new Country("Belgium", "€", 0, 0.05, 0, 0.05,true
    ), new Country("Bulgaria", "лв", 0.05, 0.15, 0.03, 0.06,false
    ), new Country("Croatia", "kn", 0.1, 0.2, 0, 0.05,false
    ), new Country("Cyprus", "€", 0, 0.1, 0, 0.05,false
    ), new Country("Czech", "Kč", 0.05, 0.1, 0, 0.05,false
    ), new Country("Denmark", "kr", 0.0, 0.05, 0, 0.05,true
    ), new Country("Estonia", "€", 0.0, 0.05, 0.05, 0.1, true
    ), new Country("Finland", "€", 0, 0.05, 0, 0.0,true
    ), new Country("France", "€", 0.05, 0.15, 0.08, 0.15,false
    ), new Country("Georgia", "₾", 0, 0.05, 0, 0.05,true
    ), new Country("Germany", "€", 0.05, 0.1, 0, 0.05,true
    ), new Country("Greece", "€", 0.05, 0.1, 0, 0.05,false
    ), new Country("Hungary", "Ft", 0.1, 0.15, 0.05, 0.1,false
    ), new Country("Iceland", "kr", 0, 0.1, 0, 0.0,true
    ), new Country("Ireland", "€", 0, 0.1, 0, 0.1,false
    ), new Country("Italy", "€", 0, 0.1, 0, 0.1,true
    ), new Country("Kazakhstan", "₸", 0.05, 0.15, 0, 0.1,true
    ), new Country("Latvia", "€", 0.07, 0.12, 0.05, 0.1,false
    ), new Country("Liechtenstein", "CHF", 0, 0.05, 0, 0.05,true
    ), new Country("Lithuania", "€", 0.08, 0.13, 0, 0.07,false
    ), new Country("Luxembourg", "€", 0.07, 0.12, 0.04, 0.1,false
    ), new Country("Macedonia", "den", 0.1, 0.15, 0, 0.1,false
    ), new Country("Malta", "€", 0.05, 0.1, 0, 0.1,false
    ), new Country("Monaco", "€", 0, 0.15, 0, 0.05,true
    ), new Country("Netherlands", "€", 0, 0.1, 0, 0.05,true
    ), new Country("Norway", "kr", 0.05, 0.1, 0, 0.04,true
    ), new Country("Poland", "zł", 0.07, 0.12, 0.08, 0.15,false
    ), new Country("Portugal", "€", 0.05, 0.12, 0, 0.05,false
    ), new Country("Romania", "lei", 0.05, 0.15, 0, 0.05,false
    ), new Country("Russia", "₽", 0.05, 0.15, 0.05, 0.1,false
    ), new Country("San Marino", "€", 0, 0.1, 0, 0.05, false
    ), new Country("Serbia", "din", 0.05, 0.15, 0, 0.05,false
    ), new Country("Slovakia", "€", 0, 0.1, 0.05, 0.1,false
    ), new Country("Slovenia", "€", 0, 0.15, 0, 0.05,false
    ), new Country("Spain", "€", 0.07, 0.13, 0, 0.05,false
    ), new Country("Sweden", "kr", 0, 0.1, 0, 0.05,true
    ), new Country("Switzerland", "CHF", 0, 0.1, 0, 0.05,true
    ), new Country("Turkey", "₺", 0.05, 0.15, 0, 0.1,false
    ), new Country("Ukraine", "₴", 0.05, 0.15, 0, 0.05,false
    ), new Country("United Kingdom", "£", 0.0, 0.0, 0.05, 0.1,true
    )};

    //    private static File file = new File("countries.txt");
    public Country getCountry(int index) {
        return countries[index];
    }

    public Country returnCountry(String countryName) {
        for (int i = 0; i < countries.length; i++) {
            if (countries[i].getCountryName().equals(countryName))
                return countries[i];
        }
        return null;
    }

    public List<String> retrieve_alternatives() {
        List<String> list = new ArrayList<String>();
        list.add("Select a country");
        for (int i = 0; i < this.countries.length; i++) {
            list.add(this.countries[i].getCountryName());
        }
        return list;
    }

    //Order of the string:  country, lowendrest, highendrest, lowendtaxi, highendtaxi, country
    //Alternatively:  country, highendrest, highendtaxi
}

