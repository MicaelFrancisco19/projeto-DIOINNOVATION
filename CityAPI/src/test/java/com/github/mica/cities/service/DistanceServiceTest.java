package com.github.mica.cities.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.data.Offset.offset;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;

import com.github.mica.cities.entities.City;
import com.github.mica.cities.repositories.CityRepository;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.geo.Point;

@SpringBootTest
class DistanceServiceTest {

  @Autowired
  private DistanceService service;

  @MockBean
  private CityRepository cityRepository;

  private City Ilhabela;
  private City iaras;

  @BeforeEach
  public void setUp() {
    iaras = new City(4928L, "Iaras", 26, 3519253, "(-22.8682003021239986,-49.1633987426758026)",
        new Point(-22.8682003021239986, -49.1633987426758026));
    Ilhabela =
        new City(4942L, "São Carlos", 26, 3520400, "(-23.7784996032714986,-45.3552017211913991)",
            new Point(-23.7784996032714986, -45.3552017211913991));
  }

  @Test
  public void shouldCalculateInMetersUsingMath() {
    given(cityRepository.findAllById(anyList())).willReturn(Arrays.asList(ibate, saoCarlos));

    Double distance = service.distanceUsingMath(4929L, 5254L, EarthRadius.METERS);

    assertThat(distance).isEqualTo(12426.810463475855);
  }

  @Test
  public void shouldCalculateInKilometersUsingMath() {
    given(cityRepository.findAllById(anyList())).willReturn(Arrays.asList(ibate, saoCarlos));

    Double distance = service.distanceUsingMath(4929L, 5254L, EarthRadius.KILOMETERS);

    assertThat(distance).isCloseTo(12.426, offset(0.001d));
  }

  @Test
  public void shouldCalculateInMilesUsingMath() {
    given(cityRepository.findAllById(anyList())).willReturn(Arrays.asList(ibate, saoCarlos));

    Double distance = service.distanceUsingMath(4929L, 5254L, EarthRadius.MILES);

    assertThat(distance).isCloseTo(7.71, offset(0.01d));
  }


  @Test
  public void shouldCalculateInMetersUsingPoints() {
    given(cityRepository.findAllById(anyList())).willReturn(Arrays.asList(ibate, saoCarlos));

    Double distance = service.distanceUsingPoints(4929L, 5254L, EarthRadius.METERS);

    assertThat(distance).isEqualTo(12426.810463475855);
  }

}