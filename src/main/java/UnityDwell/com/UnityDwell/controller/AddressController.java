package UnityDwell.com.UnityDwell.controller;

import UnityDwell.com.UnityDwell.dto.response.AddressResponse;
import UnityDwell.com.UnityDwell.dto.request.CreateOrUpdateAddressRequest;
import UnityDwell.com.UnityDwell.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public AddressResponse getHousingAddress(@Validated @RequestBody CreateOrUpdateAddressRequest request) {
        return addressService.addNewAddress(request);
    }
}