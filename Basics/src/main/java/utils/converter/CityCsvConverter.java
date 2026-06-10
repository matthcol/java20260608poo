package utils.converter;

import model.lombok.CityFr;

import java.util.Map;

public class CityCsvConverter {
    /**
     * Convert a CSV line to a CityFr object using a mapper ...
     * @param line line as an array of String representing a city
     * @param mapper mapper associating column names to column indexes
     * @return city built
     * @throws ArrayIndexOutOfBoundsException index column from mapper is out of bound
     * @throws NullPointerException column name is unknown in the mapper
     * @throws NumberFormatException integer field is read from a non integer column
     */
    public static CityFr lineCsvToCity(String[] line, Map<String, Integer> mapper){
        return CityFr.builder()
                .name(line[mapper.get("nom_standard")])
                .codeInsee(line[mapper.get("code_insee")])
                .population(Integer.parseInt(line[mapper.get("population")]))
                .area(Integer.parseInt(line[mapper.get("superficie_km2")]))
                .build();
    }
}
