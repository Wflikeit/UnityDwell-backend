package UnityDwell.com.UnityDwell.controller;

import UnityDwell.com.UnityDwell.dto.response.AddressResponse;
import UnityDwell.com.UnityDwell.dto.request.CreateOrUpdateAddressRequest;
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
    @Secured({"ROLE_ADMIN", "ROLE_EMPLOYEE", "ROLE_FLAT_OWNER"})
    @GetMapping(value = "/{id}")
    public AddressResponse getHousingAddress(@PathVariable("id") UUID addressId) {
        System.out.println("controller");
        return addressService.getAddressByHousingAssociationId(addressId);
    }
    @Secured({"ROLE_ADMIN", "ROLE_EMPLOYEE", "ROLE_FLAT_OWNER"})
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public AddressResponse addAddress(@Validated @RequestBody CreateOrUpdateAddressRequest request) {
        return addressService.addNewAddress(request);
    }
    @Secured({"ROLE_ADMIN", "ROLE_EMPLOYEE"})
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAddress(@PathVariable("id") UUID addressId) {
        addressService.deleteAddress(addressId);
    }
}