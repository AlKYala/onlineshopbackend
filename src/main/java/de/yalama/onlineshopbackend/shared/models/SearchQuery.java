package de.yalama.onlineshopbackend.shared.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * This class holds all data relevant in searches for advertisements
 */
@Getter
@Setter
@RequiredArgsConstructor
public class SearchQuery {
    private int minPrice;
    private int maxPrice;
    private String terms;
    private Set<String> searchTermsSet;
    private int categoryId;
    private int markeId;

    public void feedSearchTermsToSet() {
        if(this.terms != null) {
            this.terms = terms.toLowerCase();
            this.searchTermsSet = new HashSet<String>();
            for(String term: this.terms.split(" ")) {
                this.searchTermsSet.add(term);
            }
        }
    }
}
