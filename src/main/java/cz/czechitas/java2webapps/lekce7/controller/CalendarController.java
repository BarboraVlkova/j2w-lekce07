package cz.czechitas.java2webapps.lekce7.controller;

import cz.czechitas.java2webapps.lekce7.service.CalendarData;
import cz.czechitas.java2webapps.lekce7.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Filip Jirsák
 */

// Anotace cele tridy @Controller, metody @GetMapping , parametry v metodach @ModelAtribute
// @ModelAttribute je anotace , CalendarData data je parametr metody
// pouzijeme v prohlizeci Spring Framework

@Controller
public class CalendarController {

  private final CalendarService service;
//  diky Springu Java vytvorila sama bezparametricky konstruktor a vytvoril instnci tridy
//  tohle se nepouziva, protoze je pevne dany, ze se ma pouzit CalendarService a pri vytvoreni objektu se vzdy vytvori to same
//  public CalendarController() {
//    service = new CalendarService();
//  }

//  vytvorila jsem rucne konstruktor s parametry
//  rikam, ze chci hodnotu typu CalendarService, ale nerikam co presne to ma byt
//  anotace @Autowired = pri pouziti vice kontroleru rika, ktery konstruktor ma pouzit

  @Autowired
  public CalendarController(CalendarService service) {
    this.service = service;
  }


  @GetMapping("/")
  public String index(@ModelAttribute("calendar")CalendarData data) {
    return "index";
  }

//  METODA PRO MAPOVANI ADRESY Z PROHLIZECE (zobrazeni adresy bez cisel, otazniku, klikyhaku)
//  PRESMERUJ ME na zobrazeni hezci stranky
//  funkce format bere % jako specielni znak a dosadi do retezce dalsi parametry
//  /%s = jako string
//  /%d = decimal, cislo v desitkove soustave
  @GetMapping("/vypocet")
  public String form(int rok, String typ) {
    return String.format("redirect:/%s/%d", typ, rok);
//    chci ziskat tento zapis
//    return "/redirect/christmas/2021"
  }


  @GetMapping("/easter/{year}")
  public ModelAndView easter(@PathVariable int year) {
    return new ModelAndView("easter")
            .addObject("year", year)
            .addObject("calendar", service.easter(year));
  }

  @GetMapping("/pentecost/{year}")
  public ModelAndView pentecost(@PathVariable int year) {
    return new ModelAndView("pentecost")
            .addObject("year", year)
            .addObject("calendar", service.pentecost(year));
  }

  @GetMapping("/christmas/{year}")
  public ModelAndView christmas(@PathVariable int year) {
    return new ModelAndView("christmas")
            .addObject("year", year)
            .addObject("calendar", service.christmas(year));
  }

  @GetMapping("/sylvester/{year}")
  public ModelAndView sylvester(@PathVariable int year) {
    return new ModelAndView("sylvester")
            .addObject("year", year)
            .addObject("calendar", service.sylvester(year));
  }


}
