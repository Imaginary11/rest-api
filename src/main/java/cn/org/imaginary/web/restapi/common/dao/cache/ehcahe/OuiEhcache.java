package cn.org.imaginary.web.restapi.common.dao.cache.ehcahe;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author : Imaginary
 * @version : V1.0
 * @date : 2017/12/27 22:36
 * @see : oui cache demo
 */
@Component
public class OuiEhcache extends BaseEhCache {
    private static final String CACHE_NAME = "ouiCache";
    private static final String OUI_FILE_NAME = "oui.csv";
    @Autowired
    protected CacheManager cacheManager;

    @Override
    protected Cache getCache() {
        return cacheManager.getCache(CACHE_NAME);
    }

    @Override
    protected void initCache() throws Exception {
        ClassPathResource cpr = new ClassPathResource("oui/" + OUI_FILE_NAME);
        InputStreamReader reader = new InputStreamReader(cpr.getInputStream());
        BufferedReader br = new BufferedReader(reader);
        String str;
        String[] ouiArrayDot;
        String[] ouiArrayQuotation;
        while ((str = br.readLine()) != null) {
            if (!StringUtils.isEmpty(str) && str.startsWith("MA-")) {
                ouiArrayDot = str.split(",");
                String mac = ouiArrayDot[1];
                String company = ouiArrayDot[2];
                if (company.startsWith("\"")) {
                    ouiArrayQuotation = str.split("\"");
                    company = ouiArrayQuotation[1];
                }
                set(mac, company);
            }
        }
        br.close();
        reader.close();
    }


}
