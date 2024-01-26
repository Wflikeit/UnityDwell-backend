package UnityDwell.com.UnityDwell.controller;

import UnityDwell.com.UnityDwell.dto.listResponses.AddressesResponse;
import UnityDwell.com.UnityDwell.dto.request.CreateOrUpdateAddressRequest;
import UnityDwell.com.UnityDwell.dto.response.AddressResponse;
import UnityDwell.com.UnityDwell.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/address")
@CrossOrigin(origins = "*")
public class AddressController {
    private final AddressService addressService;

    @GetMapping(value = "/{id}")
    @Secured({"ROLE_ADMIN", "ROLE_EMPLOYEE", "ROLE_FLAT_OWNER"})
    public AddressResponse getHousingAddress(@PathVariable("id") UUID addressId) {
        return addressService.getAddressByHousingAssociationId(addressId);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @Secured({"ROLE_ADMIN", "ROLE_EMPLOYEE", "ROLE_FLAT_OWNER"})
    public AddressResponse addAddress(@Validated @RequestBody CreateOrUpdateAddressRequest request) {
        return addressService.addNewAddress(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Secured({"ROLE_ADMIN", "ROLE_EMPLOYEE"})
    public void deleteAddress(@PathVariable("id") UUID addressId) {
        addressService.deleteAddress(addressId);
    }

    @GetMapping()
    @Secured({"ROLE_ADMIN", "ROLE_EMPLOYEE", "ROLE_FLAT_OWNER"})
    public AddressesResponse getAddresses() {
        return addressService.getAddresses();
    }
}