package com.mycompany.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mycompany.repository.Customer;
import com.mycompany.repository.ValidFor;
import lombok.Data;

import javax.persistence.Embedded;
import java.util.List;
import java.util.Optional;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class CustomerDto {

    @JsonProperty(value = "@type")
    private final String type = "Customer";
    private String href;
    private String id;
    private Optional<String> name;
    private Optional<String> status;
    private Optional<String> statusReason;
    @Embedded
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
        this.name = Optional.ofNullable(customer.getName());
        this.status = Optional.ofNullable(customer.getStatus());
        this.statusReason = Optional.ofNullable(customer.getStatusReason());
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
