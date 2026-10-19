/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util.search;

import org.hibernate.search.backend.elasticsearch.analysis.ElasticsearchAnalysisConfigurationContext;
import org.hibernate.search.backend.elasticsearch.analysis.ElasticsearchAnalysisConfigurer;

/**
 *
 * @author Cartman
 */
public class CustomAnalysisConfigurer implements ElasticsearchAnalysisConfigurer{

    @Override
    public void configure(ElasticsearchAnalysisConfigurationContext context) {

        
        context.analyzer("customanalyzer")
               .custom()
               .tokenizer("standard")
               .tokenFilters("lowercase", "serbian_normalization", "asciifolding");

        
        context.tokenFilter("snowball_english")
               .type("snowball")
               .param("language", "English");

    }
    
}
