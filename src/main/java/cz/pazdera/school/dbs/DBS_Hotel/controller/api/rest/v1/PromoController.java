package cz.pazdera.school.dbs.DBS_Hotel.controller.api.rest.v1;


import cz.pazdera.school.dbs.DBS_Hotel.dto.promo.CreatePromoDto;
import cz.pazdera.school.dbs.DBS_Hotel.dto.promo.UpdatePromoDto;
import cz.pazdera.school.dbs.DBS_Hotel.model.PromoCode;
import cz.pazdera.school.dbs.DBS_Hotel.service.PromoService;
import javassist.NotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/promo")
public class PromoController {

    private final PromoService promoService;
    private static final Logger console = LogManager.getLogger(PromoController.class);

    @Autowired
    public PromoController(PromoService PromoService) {
        this.promoService = PromoService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces  = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public PromoCode createPromo(@Valid@RequestBody CreatePromoDto body) {
        return promoService.persist(body);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}")
    public void deletePromo(@PathVariable(value = "id") Integer id) throws NotFoundException {
        this.promoService.delete(id);
    }


    @GetMapping(value = "/{id}",produces  = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public PromoCode getPromo(@PathVariable(value = "id") Integer id) throws NotFoundException {
        return this.promoService.get(id);
    }

    @GetMapping(produces  = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public List<PromoCode> getAllPromos(){
        return this.promoService.getAll();
    }

    @GetMapping(produces  = MediaType.APPLICATION_JSON_VALUE,value = "/active")
    @ResponseStatus(HttpStatus.OK)
    public List<PromoCode> getAllActivePromos(){
        return this.promoService.getAllActive();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,value = "/{number}",produces  = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public PromoCode updatePromo(@PathVariable(value = "number") Integer number,@Valid@RequestBody UpdatePromoDto body) throws NotFoundException {
        return this.promoService.update(number,body);
    }

}
