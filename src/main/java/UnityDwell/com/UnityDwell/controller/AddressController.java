package UnityDwell.com.UnityDwell.controller;

import UnityDwell.com.UnityDwell.dto.AddressResponse;
import UnityDwell.com.UnityDwell.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/address")
public class AddressController {
    private final AddressService addressService;

    @GetMapping(value = "/{id}")
    public AddressResponse getHousingAddress(@PathVariable("id") UUID addressId) {
        return addressService.getAddressByHousingAssociationId(addressId);
    }
}