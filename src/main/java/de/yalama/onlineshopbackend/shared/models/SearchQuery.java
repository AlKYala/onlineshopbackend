package de.yalama.onlineshopbackend.shared.models;

import de.yalama.onlineshopbackend.Advertisement.model.Advertisement;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * This class holds all data relevant in searches for advertisements
 */
@Getter
@Setter
@RequiredArgsConstructor
public class SearchQuery {
    private Integer minPrice;
    private Integer maxPrice;
    private String terms;
    private Set<String> searchTermsSet;
    private Long categoryId;
    private Long markeId;

    public void feedSearchTermsToSet() {
        if(this.terms != null) {
            this.terms = terms.toLowerCase();
            this.searchTermsSet = new HashSet<String>();
            for(String term: this.terms.split(" ")) {
                this.searchTermsSet.add(term);
            }
        }
    }

    /**
     * Sets advertisements null that do no match the query
     * AtomicInteger shared by all filter methods to count the number of deleted ads for easier assembly later
     */
    public void filter(Advertisement[] ads, AtomicInteger deleted) {
        for(int i = 0; i < ads.length; i++) {
            if(ads[i] == null) {
                continue;
            }
            Advertisement tempAd = ads[i];
            boolean matchesPrice = this.matchesPrice(tempAd);

            boolean matchesMarke = this.matchesMarke(tempAd);

            boolean matchesCategory = this.matchesCategory(tempAd);

            if(!matchesPrice || !matchesMarke || !matchesCategory) {
                ads[i] = null;
                deleted.incrementAndGet();
                continue;
            }

            boolean matchesTerms = this.adContainsSearchTerm(tempAd);

            if(!matchesTerms) {
                deleted.incrementAndGet();
                ads[i] = null;
            }

        }
    }

    public Advertisement[] getRemainingAds(Advertisement[] filtered, AtomicInteger deleted) {
        Advertisement[] remainingAds = new Advertisement[filtered.length - deleted.get()];
        int rIndex = 0;
        for(Advertisement ad: filtered) {
            if(ad != null) {
                remainingAds[rIndex] = ad;
                rIndex++;
            }
        }
        return remainingAds;
    }

    private boolean matchesCategory(Advertisement ad) {
        return (this.categoryId == null) ? true : ad.getMarke().getCategory().getId() == this.getCategoryId();
    }

    private boolean matchesMarke(Advertisement ad) {
        return (this.markeId == null) ? true : ad.getMarke().getId() == this.getMarkeId();
    }

    private boolean matchesPrice(Advertisement ad) {
        return ad.getPrice() <= this.getMaxPrice() && ad.getPrice() >= this.getMinPrice();
    }

    private boolean adContainsSearchTerm(Advertisement ad) {
        if(this.terms == null || this.terms.length() == 0) {
            return true;
        }
        String[] adTerms = String.format("%s %s", ad.getTitle(), ad.getDescription()).toLowerCase().split(" ");
        for(String term: adTerms) {
            if(this.searchTermsSet.contains(term)) {
                return true;
            }
        }
        return false;
    }
}
