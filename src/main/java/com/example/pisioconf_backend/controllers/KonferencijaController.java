package com.example.pisioconf_backend.controllers;

import com.example.pisioconf_backend.exception.NotFoundException;
import com.example.pisioconf_backend.models.dto.Konferencija;
import com.example.pisioconf_backend.models.dto.Korisnik;
import com.example.pisioconf_backend.models.dto.Ocjena;
import com.example.pisioconf_backend.models.entities.KonferencijaEntity;
import com.example.pisioconf_backend.models.requests.KonferencijaRequest;
import com.example.pisioconf_backend.services.KonferencijaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/konferencije")
public class KonferencijaController {

    private final KonferencijaService konferencijaService;

    public KonferencijaController(KonferencijaService konferencijaService) {
        this.konferencijaService = konferencijaService;
    }
    @GetMapping("/{idModeratora}/moderator")
    public List<Konferencija> findByModeratorId(@PathVariable Integer idModeratora) throws NotFoundException {
        return konferencijaService.findAllKonferencijeByModerator(idModeratora);
    }
    @GetMapping("/searchConfModerator/{idModeratora}")
    public List<Konferencija> findAllKonferencijeByParamsModerator(@PathVariable Integer idModeratora,@RequestParam(value = "status", required = false) Boolean status,@RequestParam(value = "start", required = false) Date start,@RequestParam(value = "end", required = false) Date end, @RequestParam(value = "naziv", required = false) String naziv)
    {
        List<Konferencija> konferencije= konferencijaService.findAllKonferencijeByModerator(idModeratora);
        List<Konferencija> filtriraneKonferencije= new ArrayList<>();
        if(status != null && start == null && end == null && naziv ==null)
        {
           return filtriraneKonferencije = konferencije.stream()
                    .filter(konferencija -> konferencija.getStatus().equals(status))
                    .collect(Collectors.toList());

        }
        else if(status == null && start != null && end != null && naziv ==null)
        {
            return filtriraneKonferencije = konferencije.stream()
                    .filter(konferencija -> konferencija.getStartTime().before(start) && konferencija.getEndTime().after(end))
                    .collect(Collectors.toList());
        }
        else if(naziv !=null && status == null && start == null && end == null )
        {
            return filtriraneKonferencije = konferencije.stream()
                    .filter(konferencija -> konferencija.getNaziv().startsWith(naziv))
                    .collect(Collectors.toList());
        } else if(status != null && start != null && end != null && naziv ==null )
        {
            return filtriraneKonferencije = konferencije.stream()
                    .filter(konferencija -> konferencija.getStartTime().before(start) && konferencija.getEndTime().after(end) && konferencija.getStatus().equals(status))
                    .collect(Collectors.toList());
        }
        else if(status != null && start == null && end == null && naziv !=null )
        {
            return filtriraneKonferencije = konferencije.stream()
                    .filter(konferencija -> konferencija.getStatus().equals(status) && konferencija.getNaziv().startsWith(naziv))
                    .collect(Collectors.toList());
        }
        else if(status == null && start != null && end != null && naziv !=null )
        {
            return filtriraneKonferencije = konferencije.stream()
                    .filter(konferencija -> konferencija.getStartTime().before(start) && konferencija.getEndTime().after(end) && konferencija.getNaziv().startsWith(naziv))
                    .collect(Collectors.toList());
        }
        else if(status != null && start != null && end != null && naziv !=null )
        {
            return filtriraneKonferencije = konferencije.stream()
                    .filter(konferencija -> konferencija.getStartTime().before(start) && konferencija.getEndTime().after(end)
                            && konferencija.getStatus().equals(status) && konferencija.getNaziv().startsWith(naziv))
                    .collect(Collectors.toList());
        }
        else {
            return konferencijaService.findAllKonferencijeByModerator(idModeratora);
        }
//        //return konferencijaService.findAllKonferencijeByStatusAndDatumAndNaziv(status,datum,naziv);

    }




    @GetMapping("/searchConf")
    public List<Konferencija> findAllKonferencijeByParams(@RequestParam(value = "status", required = false) Boolean status,@RequestParam(value = "start", required = false) Date start,@RequestParam(value = "end", required = false) Date end, @RequestParam(value = "naziv", required = false) String naziv)
    {
        if(status != null && start == null && end == null && naziv ==null)
        {
            return konferencijaService.findAllKonferencijeByStatus(status);
        }
        else if(status == null && start != null && end != null && naziv ==null)
        {
            return konferencijaService.findAllKonferencijeByDatum(start,end);
        }
        else if(naziv !=null && status == null && start == null && end == null )
        {
            return konferencijaService.findAllKonferencijeByNaziv(naziv);
        } else if(status != null && start != null && end != null && naziv ==null )
        {
            return konferencijaService.findAllKonferencijeByStatusAndDatum(status,start,end);
        }
        else if(status != null && start == null && end == null && naziv !=null )
        {
            return konferencijaService.findAllKonferencijeByStatusAndNaziv(status,naziv);
        }
        else if(status == null && start != null && end != null && naziv !=null )
        {
            return konferencijaService.findAllKonferencijeByDatumAndNaziv(start,end,naziv);
        }
        else if(status != null && start != null && end != null && naziv !=null )
        {
            return konferencijaService.findAllKonferencijeByStatusAndDatumAndNaziv(status,start,end,naziv);
        }
        else {
            return konferencijaService.findAll();
        }
//        //return konferencijaService.findAllKonferencijeByStatusAndDatumAndNaziv(status,datum,naziv);

    }


    @GetMapping("/all")
    List<Konferencija> findAll() {
        return konferencijaService.findAll();
    }
   /* @GetMapping("/{idModeratora}/moderator-conf")
    List<Konferencija> findAllByModeratorId(@PathVariable Integer idModeratora) {
        return konferencijaService.findAllByModeratorId(idModeratora);
    }*/



    @GetMapping("/{id}")
    public Konferencija findById(@PathVariable Integer id) throws NotFoundException {
        return konferencijaService.findById(id);
    }

    @GetMapping("/{idPosjetioca}/posjetilac")
    public List<Konferencija> findByPosjetilacId(@PathVariable Integer idPosjetioca) throws NotFoundException {
       return konferencijaService.findAllKonferencijeByPosjetilac(idPosjetioca);
    }

    @GetMapping("/searchConfPosjetilac/{idPosjetioca}")
    public List<Konferencija> findAllKonferencijeByParamsPosjetilac(@PathVariable Integer idPosjetioca,@RequestParam(value = "status", required = false) Boolean status,@RequestParam(value = "start", required = false) Date start,@RequestParam(value = "end", required = false) Date end, @RequestParam(value = "naziv", required = false) String naziv)
    {
        List<Konferencija> konferencije= konferencijaService.findAllKonferencijeByPosjetilac(idPosjetioca);
        List<Konferencija> filtriraneKonferencije= new ArrayList<>();
        if(status != null && start == null && end == null && naziv ==null)
        {
            return filtriraneKonferencije = konferencije.stream()
                    .filter(konferencija -> konferencija.getStatus().equals(status))
                    .collect(Collectors.toList());

        }
        else if(status == null && start != null && end != null && naziv ==null)
        {
            return filtriraneKonferencije = konferencije.stream()
                    .filter(konferencija -> konferencija.getStartTime().before(start) && konferencija.getEndTime().after(end))
                    .collect(Collectors.toList());
        }
        else if(naziv !=null && status == null && start == null && end == null )
        {
            return filtriraneKonferencije = konferencije.stream()
                    .filter(konferencija -> konferencija.getNaziv().startsWith(naziv))
                    .collect(Collectors.toList());
        } else if(status != null && start != null && end != null && naziv ==null )
        {
            return filtriraneKonferencije = konferencije.stream()
                    .filter(konferencija -> konferencija.getStartTime().before(start) && konferencija.getEndTime().after(end) && konferencija.getStatus().equals(status))
                    .collect(Collectors.toList());
        }
        else if(status != null && start == null && end == null && naziv !=null )
        {
            return filtriraneKonferencije = konferencije.stream()
                    .filter(konferencija -> konferencija.getStatus().equals(status) && konferencija.getNaziv().startsWith(naziv))
                    .collect(Collectors.toList());
        }
        else if(status == null && start != null && end != null && naziv !=null )
        {
            return filtriraneKonferencije = konferencije.stream()
                    .filter(konferencija -> konferencija.getStartTime().before(start) && konferencija.getEndTime().after(end) && konferencija.getNaziv().startsWith(naziv))
                    .collect(Collectors.toList());
        }
        else if(status != null && start != null && end != null && naziv !=null )
        {
            return filtriraneKonferencije = konferencije.stream()
                    .filter(konferencija -> konferencija.getStartTime().before(start) && konferencija.getEndTime().after(end)
                            && konferencija.getStatus().equals(status) && konferencija.getNaziv().startsWith(naziv))
                    .collect(Collectors.toList());
        }
        else {
            return konferencijaService.findAllKonferencijeByPosjetilac(idPosjetioca);
        }
//        //return konferencijaService.findAllKonferencijeByStatusAndDatumAndNaziv(status,datum,naziv);

    }


    @GetMapping("/nezavrsene")
    List<KonferencijaEntity> findAllWhereKonferencijaIsNotFInished()
    {
        return  konferencijaService.findAllWhereKonferencijaIsNotFinished();
    }
    @GetMapping("/{idKonferencije}/ocjene")
    public List<Ocjena> findAllOcjeneForKonferencija(@PathVariable Integer idKonferencije) throws NotFoundException{
        return konferencijaService.getAllOcjeneByKorisnikId(idKonferencije);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        konferencijaService.delete(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Konferencija insert(@RequestBody @Valid KonferencijaRequest konferencijaRequest) throws NotFoundException {
        return konferencijaService.insert(konferencijaRequest);
    }

    @PatchMapping("/{id}")
    public Konferencija update(@PathVariable Integer id, @RequestBody KonferencijaRequest konferencijaRequest) throws NotFoundException {
        return konferencijaService.update(id, konferencijaRequest);
    }


}
