package me.mtuc.conference.common.controller;

import lombok.RequiredArgsConstructor;
import me.mtuc.conference.common.service.PaymentService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payment/")
public class PaymentRestController {

    private final PaymentService paymentService;

    @PostMapping("/registrations/{id}")
    public void paidRegistrationsFee(@PathVariable(name = "id") Long id) {
        paymentService.paidRegistrationsFee(id);
    }

    @PostMapping("/booth/{id}")
    public void paidBoothFee(@PathVariable(name = "id") Long id) {

    }
}
