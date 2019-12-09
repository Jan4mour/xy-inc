package com.xyinc.resource;

import com.xyinc.core.GenericError;
import com.xyinc.model.PointOfInterest;
import com.xyinc.resource.dto.PointOfInterestDto;
import com.xyinc.resource.dto.PointOfInterestRequest;
import com.xyinc.service.PointOfInterestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;
import java.util.Optional;

@RestController
@Validated
@RequestMapping("/point-of-interest")
public class PointOfInterestResource {

    /**
     * logger
     */
    public static final Logger LOGGER = LoggerFactory.getLogger(PointOfInterestResource.class);

    @Autowired
    private PointOfInterestService pointOfInterestService;

    /**
     * Serviço que cria um objeto do tipo da entidade.
     *
     * @param point
     * @return
     */
    @PostMapping
    public ResponseEntity<PointOfInterest> registerPointOfInterest(@Valid @RequestBody PointOfInterestDto point, BindingResult brs) {
        if (!brs.hasErrors()) {
            try {
                LOGGER.info("Classe: {}, criando registro: {}", getClass().getName(), point);
                PointOfInterest poi = new PointOfInterest(point);
                pointOfInterestService.registerPointOfInterest(poi);
                return ResponseEntity.ok(poi);
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "registerPointOfInterest", e);
            }
        }
        return new ResponseEntity(new GenericError("As coordenadas não podem ser negativas."), HttpStatus.BAD_REQUEST);
    }

    /**
     * Serviço que busca todos os registros
     *
     * @return
     */
    @GetMapping
    public ResponseEntity retrieveAllPointOfInterest() {

        try {
            Optional<List<PointOfInterest>> listaRetorno = pointOfInterestService.retrieveAllPointOfInterest();

            if (!listaRetorno.isPresent()) {
                LOGGER.info("Nenhum registro encontrado");
                return new ResponseEntity(new GenericError("Nenhum registro encontrado"), HttpStatus.NO_CONTENT);
            }
            return ResponseEntity.ok(listaRetorno);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "buscarTodos", e);
        }
    }

    /**
     * Serviço que busca por proximidade.
     *
     * @return
     */
    @RequestMapping(value = "/find-by-proximity", method = RequestMethod.GET)
    public ResponseEntity<List<PointOfInterest>> findByProximity(
            @PositiveOrZero(message = "Coordenada x deveria ser positiva") @RequestParam(value = "Coordenada x") Double coordinateX,
            @PositiveOrZero(message = "Coordenada y deveria ser positiva") @RequestParam(value = "Coordenada y") Double coordinateY,
            @PositiveOrZero(message = "Distancia maxima deveria ser maior ou igual a Zero") @RequestParam(value = "Distancia Max") Double dMax) {

        List<PointOfInterest> returnPoints = pointOfInterestService.findByProximity(coordinateX, coordinateY, dMax);
        if (returnPoints.isEmpty()) {
            return new ResponseEntity(new GenericError("Não foi encontrado nenhum ponto de interesse na proximidade."), HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok().body(returnPoints);
    }

    /**
     * Serviço que busca por id um registro da entidade.
     *
     * @param id
     * @return
     */
    @GetMapping("/id/{id}")
    public ResponseEntity<PointOfInterestDto> findPointOfInterestById(@PathVariable("id") Long id) {
        try {
            LOGGER.info("Classe: {}, buscando com o id {}", getClass().getName(), id);
            Optional<PointOfInterest> point = pointOfInterestService.findPointOfInterestById(id);

            if (!point.isPresent()) {
                LOGGER.error("Id {} não encontrado na base.", id);
                return new ResponseEntity(new GenericError("Id " + id + " não encontrado na base."), HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok(new PointOfInterestDto(point.get()));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "findPointOfInterestById", e);
        }
    }


    /**
     * Serviço que remove um registro da entidade a partir do ID.
     *
     * @param id
     * @return
     */
    @DeleteMapping("/id/{id}")
    public ResponseEntity deletePointOfInterest(@PathVariable("id") Long id) {
        try {
            LOGGER.info("Classe: {}, removendo registro c/ id {}", getClass().getName(), id);
            Optional<PointOfInterest> point = pointOfInterestService.findPointOfInterestById(id);

            if (!point.isPresent()) {
                LOGGER.error("Não foi possível remover. O id {} não foi encontrado.", id);
                return new ResponseEntity(
                        new GenericError("Não foi possível remover. O id " + id + " não foi encontrado."),
                        HttpStatus.NOT_FOUND);
            }

            pointOfInterestService.deletePointOfInterest(point.get());

            return ResponseEntity.ok(point);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "deletePointOfInterest", e);
        }
    }

}
