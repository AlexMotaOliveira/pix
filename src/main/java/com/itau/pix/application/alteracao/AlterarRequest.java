package com.itau.pix.application.alteracao;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

/**
 * AlterarRequest
 */
@SuppressWarnings({"hiding","static-method","unused"})

@JsonTypeName("alterar_request")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class AlterarRequest {

  private String tipoConta;

  private int numeroAgencia;

  private int numeroConta;

  private String nomeCorrentista;

  private String sobrenomeCorrentista;

  public AlterarRequest() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public AlterarRequest(String tipoConta, int numeroAgencia, int numeroConta, String nomeCorrentista) {
    this.tipoConta = tipoConta;
    this.numeroAgencia = numeroAgencia;
    this.numeroConta = numeroConta;
    this.nomeCorrentista = nomeCorrentista;
  }

  public AlterarRequest tipoConta(String tipoConta) {
    this.tipoConta = tipoConta;
    return this;
  }

  /**
   * Get tipoConta
   * @return tipoConta
  */
  @NotNull @Size(max = 10) 
  @JsonProperty("tipoConta")
  public String getTipoConta() {
    return tipoConta;
  }

  public void setTipoConta(String tipoConta) {
    this.tipoConta = tipoConta;
  }

  public AlterarRequest numeroAgencia(int numeroAgencia) {
    this.numeroAgencia = numeroAgencia;
    return this;
  }

  /**
   * Get numeroAgencia
   * @return numeroAgencia
  */
  @NotNull @Valid 
  @JsonProperty("numeroAgencia")
  public int getNumeroAgencia() {
    return numeroAgencia;
  }

  public void setNumeroAgencia(int numeroAgencia) {
    this.numeroAgencia = numeroAgencia;
  }

  public AlterarRequest numeroConta(int numeroConta) {
    this.numeroConta = numeroConta;
    return this;
  }

  /**
   * Get numeroConta
   * @return numeroConta
  */
  @NotNull @Valid 
  @JsonProperty("numeroConta")
  public int getNumeroConta() {
    return numeroConta;
  }

  public void setNumeroConta(int numeroConta) {
    this.numeroConta = numeroConta;
  }

  public AlterarRequest nomeCorrentista(String nomeCorrentista) {
    this.nomeCorrentista = nomeCorrentista;
    return this;
  }

  /**
   * Get nomeCorrentista
   * @return nomeCorrentista
  */
  @NotNull @Size(max = 30) 
  @JsonProperty("nomeCorrentista")
  public String getNomeCorrentista() {
    return nomeCorrentista;
  }

  public void setNomeCorrentista(String nomeCorrentista) {
    this.nomeCorrentista = nomeCorrentista;
  }

  public AlterarRequest sobrenomeCorrentista(String sobrenomeCorrentista) {
    this.sobrenomeCorrentista = sobrenomeCorrentista;
    return this;
  }

  /**
   * Get sobrenomeCorrentista
   * @return sobrenomeCorrentista
  */
  @Size(max = 45) 
  @JsonProperty("sobrenomeCorrentista")
  public String getSobrenomeCorrentista() {
    return sobrenomeCorrentista;
  }

  public void setSobrenomeCorrentista(String sobrenomeCorrentista) {
    this.sobrenomeCorrentista = sobrenomeCorrentista;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AlterarRequest alterarRequest = (AlterarRequest) o;
    return Objects.equals(this.tipoConta, alterarRequest.tipoConta) &&
        Objects.equals(this.numeroAgencia, alterarRequest.numeroAgencia) &&
        Objects.equals(this.numeroConta, alterarRequest.numeroConta) &&
        Objects.equals(this.nomeCorrentista, alterarRequest.nomeCorrentista) &&
        Objects.equals(this.sobrenomeCorrentista, alterarRequest.sobrenomeCorrentista);
  }

  @Override
  public int hashCode() {
    return Objects.hash(tipoConta, numeroAgencia, numeroConta, nomeCorrentista, sobrenomeCorrentista);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AlterarRequest {\n");
    sb.append("    tipoConta: ").append(toIndentedString(tipoConta)).append("\n");
    sb.append("    numeroAgencia: ").append(toIndentedString(numeroAgencia)).append("\n");
    sb.append("    numeroConta: ").append(toIndentedString(numeroConta)).append("\n");
    sb.append("    nomeCorrentista: ").append(toIndentedString(nomeCorrentista)).append("\n");
    sb.append("    sobrenomeCorrentista: ").append(toIndentedString(sobrenomeCorrentista)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

