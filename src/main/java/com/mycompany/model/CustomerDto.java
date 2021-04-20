package com.mycompany.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mycompany.repository.Customer;
import com.mycompany.repository.ValidFor;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class CustomerDto {

    private String href;

    private String id;

    private String name;

    private String status;

    private String statusReason;

    private ValidFor validFor;

    @JsonProperty(value = "engagedParty")
    private EngagedPartyDto engagedParty;

    @JsonProperty(value = "account")
    private List<AccountDto> accounts;

    @JsonProperty(value = "agreement")
    private List<AgreementDto> agreements;

    @JsonProperty(value = "characteristic")
    private List<CharacteristicDto> characteristics;

    @JsonProperty(value = "creditProfile")
    private List<CreditProfileDto> creditProfiles;

    @JsonProperty(value = "paymentMethod")
    private List<PaymentMethodDto> paymentMethods;

    @JsonProperty(value = "relatedParty")
    private List<RelatedPartyDto> relatedParties;

    @JsonProperty(value = "contactMedium")
    private List<ContactMediumDto> contactMediumDtoList;

    public void setCustomer(Customer customer) {
        this.href = customer.getHref();
        this.id = customer.getId();
        this.name = customer.getName();
        this.status = customer.getStatus();
        this.statusReason = customer.getStatusReason();
        this.validFor = customer.getValidFor();

    }

    @Override
    public String toString() {
        return "CustomerDto{" +
                "href='" + href + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", statusReason='" + statusReason + '\'' +
                ", validFor=" + validFor +
                ", engagedParty=" + engagedParty +
                ", accounts=" + accounts +
                ", agreements=" + agreements +
                ", characteristics=" + characteristics +
                ", creditProfiles=" + creditProfiles +
                ", paymentMethods=" + paymentMethods +
                ", relatedParties=" + relatedParties +
                ", contactMediumDtoList=" + contactMediumDtoList +
                '}';
    }
}
