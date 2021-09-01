package br.com.jcr.wishlist.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResultData<T> {

    private Integer status;

    private String mensagem;

    private T retorno;

    public ResultData(Integer status, String mensagem){
        this.status = status;
        this.mensagem = mensagem;
    }

}