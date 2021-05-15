package cz.czechitas.java2webapps.lekce7.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;

/**
 * @author Filip Jirsák
 */

// @Service = oznacuje objekt, kde je vykonny kod (logika aplikace)
//

@Service
public class CalendarService {

  public int getCurrentYear() {
    return LocalDate.now().getYear();
  }

  public CalendarData christmas(int year) {
    LocalDate date = LocalDate.of(year, Month.DECEMBER, 24);
    return new CalendarData()
            .append("Štědrý den", date)
            .append("1. Svátek vánoční", date.plusDays(1L))
            .append("2. Svátek vánoční", date.plusDays(2L));
  }

    public CalendarData sylvester(int year) {
    LocalDate date = LocalDate.of(year, Month.DECEMBER, 31);
    return new CalendarData()
            .append("Silvestr", date)
            .append("Nový rok", date.plusDays(1L));
  }

  public CalendarData easter(int year) {
    LocalDate date = easterSunday(year);
    return new CalendarData()
            .append("Zelený čtvrtek", date.minusDays(3L))
            .append("Velký pátek", date.minusDays(2L))
            .append("Bílá sobota", date.minusDays(1L))
            .append("Boží hod Velikonoční", date)
            .append("Pondělí Velikonoční", date.plusDays(1L));
  }

  public CalendarData pentecost(int year) {
    LocalDate date = easterSunday(year).plusDays(49L);
    return new CalendarData()
            .append("Letnice", date);
  }

  /**
   * Compute date of Easter Sunday.
   *
   * @param year A year between 1900 and 2099.
   * @return Date of Easter Sunday.
   * @see <a href="https://cs.wikipedia.org/wiki/V%C3%BDpo%C4%8Det_data_Velikonoc">Výpočet data Velikonoc</a>
   */
  private LocalDate easterSunday(int year) {
    if (year < 1900) {
      throw new IllegalArgumentException("I can't calculate the date of Easter before year 1900.");
    }
    int a = year % 19;
    int b = year % 4;
    int c = year % 7;
    int m = 24;
    int n = 5;
    int d = (19 * a + m) % 30;
    int e = (n + 2 * b + 4 * c + 6 * d) % 7;

    int u = d + e - 9;
    if (u == 25 && d == 28 && e == 6 && a > 10) {
      return LocalDate.of(year, 4, 18);
    }
    if (u >= 1 && u <= 25) {
      return LocalDate.of(year, 4, u);
    }
    if (u > 25) {
      return LocalDate.of(year, 4, u - 7);
    }
    return LocalDate.of(year, 3, 22 + d + e);
  }
}
