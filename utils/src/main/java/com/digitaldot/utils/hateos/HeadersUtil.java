package com.digitaldot.utils.hateos;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class HeadersUtil {
    public HttpHeaders getHeadersPage(String itens, String totalItens, String totalPages) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Itens", itens);
        headers.add("Total-Itens", totalItens);
        headers.add("Total-Pages", totalPages);
        return headers;
    }
}
