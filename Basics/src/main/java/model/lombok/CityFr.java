package model.lombok;

import lombok.*;

// constructors
@NoArgsConstructor
@RequiredArgsConstructor
// @AllArgsConstructor(staticName = "of")  // frozen model => CityFr.of("31055", "Toulouse", 500_000)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
// getter + setter + toString + equals + hashCode
// @Data
@Getter
@Setter
@ToString(of = {"codeInsee", "name", "population"})
public class CityFr {
    private String codeInsee;

    @NonNull
    private String name;

    private int population;

    private int area;

    private String departmentCode;

    private int averageAltitude;
}
