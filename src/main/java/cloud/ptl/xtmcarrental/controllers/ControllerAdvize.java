package cloud.ptl.xtmcarrental.controllers;

import cloud.ptl.xtmcarrental.converters.editors.CarPropertyEditor;
import cloud.ptl.xtmcarrental.converters.editors.ClientPropertyEditor;
import cloud.ptl.xtmcarrental.dto.CarDTO;
import cloud.ptl.xtmcarrental.dto.ClientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvize {

    @Autowired
    private CarPropertyEditor carPropertyEditor;

    @Autowired
    private ClientPropertyEditor clientPropertyEditor;

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        webDataBinder.registerCustomEditor(CarDTO.class, this.carPropertyEditor);
        webDataBinder.registerCustomEditor(ClientDTO.class, this.clientPropertyEditor);
    }
}
