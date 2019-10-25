package com.bbiernat;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TopWords {

    public static List<String> top3(String s) {
        List<String> output = new ArrayList<>();

        s = s.replaceAll("[/()?:!.,;{}_-]+", " ");
        String[] tempS = s.split(" ");
        TreeSet<String> setS = new TreeSet<>();
        Map<Integer, String> countedWords = new HashMap<>();

        for(String word : tempS) {
            if(!word.matches("\\S+") || word.matches("\\d+") || !Character.isLetter(word.charAt(0))) continue;
            setS.add(word);
        }

        List<String> newSetS = new ArrayList<>(setS);
        Collections.reverse(newSetS);

        for(String compWord : newSetS) {
            String pattern = "\\b" + compWord + "\\b";
            Matcher m = Pattern.compile(pattern).matcher(s);
            int hits = 0;
            while (m.find()) hits++;
            System.out.println(compWord + " : " + hits);
            countedWords.put(hits, compWord);
        }

        int outputCount = countedWords.size() > 2 ? 3 : countedWords.size();
        List<Integer> sortedKeys = new ArrayList<>(countedWords.keySet());
        sortedKeys.sort(Collections.reverseOrder());

        for(int key : sortedKeys){
            if(outputCount == 0) break;
            output.add((countedWords.get(key)).toLowerCase());
            outputCount--;
        }
        return output;
    }

    @Test
    public void sampleTests() {
        assertEquals(Arrays.asList("e", "d", "a"),    TopWords.top3("a a a  b  c c  d d d d  e e e e e"));
        assertEquals(Arrays.asList("e", "ddd", "aa"), TopWords.top3("e e e e DDD ddd DdD: ddd ddd aa aA Aa, bb cc cC e e e"));
        assertEquals(Arrays.asList("won't", "wont"),  TopWords.top3("  //wont won't won't "));
        assertEquals(Arrays.asList("e"),              TopWords.top3("  , e   .. "));
        assertEquals(Arrays.asList(),                 TopWords.top3("  ...  "));
        assertEquals(Arrays.asList(),                 TopWords.top3("  '  "));
        assertEquals(Arrays.asList(),                 TopWords.top3("  '''  "));
        assertEquals(Arrays.asList("a", "of", "on"),  TopWords.top3(Stream
                .of("In a village of La Mancha, the name of which I have no desire to call to",
                        "mind, there lived not long since one of those gentlemen that keep a lance",
                        "in the lance-rack, an old buckler, a lean hack, and a greyhound for",
                        "coursing. An olla of rather more beef than mutton, a salad on most",
                        "nights, scraps on Saturdays, lentils on Fridays, and a pigeon or so extra",
                        "on Sundays, made away with three-quarters of his income.")
                .collect(Collectors.joining("\n")) ));
        String firstString = "dViZ!SFCtsX FwGOMmqTr_ftDsTWq Osfvw G'W dViZ-WnBV'KK,SFCtsX BuKOM'fi;FoheDGnA " +
                "WnBV'KK dViZ!FoheDGnA G'W-WnBV'KK FoheDGnA FoheDGnA_SFCtsX-FoheDGnA-ITXLN/Osfvw dViZ G'W Osfvw " +
                "FwGOMmqTr ftDsTWq G'W BuKOM'fi WnBV'KK.FoheDGnA.FwGOMmqTr;Osfvw!WnBV'KK!dViZ WnBV'KK,FoheDGnA " +
                "ITXLN.WnBV'KK,FoheDGnA FoheDGnA WnBV'KK-WnBV'KK-G'W!Osfvw!SFCtsX WnBV'KK WnBV'KK dViZ dViZ WnBV'KK " +
                "Osfvw WnBV'KK SFCtsX-ITXLN;ftDsTWq;BuKOM'fi-SFCtsX FoheDGnA,ftDsTWq.SFCtsX:BuKOM'fi;Osfvw WnBV'KK " +
                "WnBV'KK_dViZ.WnBV'KK.FoheDGnA.Osfvw SFCtsX FoheDGnA_WnBV'KK Osfvw;FoheDGnA:Osfvw ftDsTWq " +
                "G'W?FoheDGnA FoheDGnA?FoheDGnA WnBV'KK G'W/SFCtsX:Osfvw ITXLN dViZ!Osfvw FoheDGnA G'W:ITXLN " +
                "FoheDGnA FoheDGnA BuKOM'fi-Osfvw SFCtsX Osfvw ITXLN!SFCtsX:ftDsTWq ITXLN BuKOM'fi?G'W ftDsTWq " +
                "FoheDGnA_SFCtsX;Osfvw Osfvw-WnBV'KK,Osfvw,FoheDGnA WnBV'KK_Osfvw G'W/G'W/SFCtsX?FoheDGnA?FoheDGnA " +
                "Osfvw SFCtsX_G'W,dViZ,WnBV'KK FoheDGnA G'W SFCtsX Osfvw/G'W/Osfvw dViZ?FoheDGnA " +
                "FoheDGnA?Osfvw?WnBV'KK WnBV'KK/SFCtsX Osfvw G'W-G'W WnBV'KK-WnBV'KK/BuKOM'fi:ITXLN:Osfvw ";
        assertEquals(Arrays.asList("fohedgna", "wnbv'kk", "osfvw"), TopWords.top3(firstString));
    }

    @Test
    public void advancedTest() {
        String input = "zdo aE'kIYZTBt,FxTlpwa-WDZ:YveJOb wrUkqEZcxg YveJOb,wJfH_vResQTh YveJOb:hkQn-vResQTh VcYXqUWP'u?oIkwYu/jvRATDnY YveJOb VcYXqUWP'u wJfH zdo jvRATDnY_mZN'RUz,wrUkqEZcxg VcYXqUWP'u,aE'kIYZTBt;mPs;mZN'RUz mZN'RUz,jvRATDnY wJfH-YHkizQFM-wJfH.wrUkqEZcxg zdo vResQTh wrUkqEZcxg rW'sGEpz!WDZ mZN'RUz?vResQTh!vResQTh_aE'kIYZTBt aE'kIYZTBt vResQTh jvRATDnY!kcCjPMapPE/FxTlpwa jvRATDnY rW'sGEpz zdo,YHkizQFM wrUkqEZcxg WDZ_wrUkqEZcxg jvRATDnY/YHkizQFM-mPs FxTlpwa zdo zdo,zdo oIkwYu,FxTlpwa,jvRATDnY,mPs jvRATDnY VcYXqUWP'u-oIkwYu/aE'kIYZTBt;YveJOb VcYXqUWP'u mZN'RUz!kcCjPMapPE FNt oIkwYu,mZN'RUz_rW'sGEpz YHkizQFM FNt FxTlpwa,vResQTh YveJOb VcYXqUWP'u kcCjPMapPE wrUkqEZcxg jvRATDnY:zdo YHkizQFM nMNLC'V?mZN'RUz oIkwYu.FxTlpwa jvRATDnY YveJOb/wrUkqEZcxg,YveJOb oIkwYu FxTlpwa?FNt jvRATDnY.rW'sGEpz/kcCjPMapPE?wJfH FxTlpwa FxTlpwa mZN'RUz kcCjPMapPE wrUkqEZcxg YveJOb:rW'sGEpz,wrUkqEZcxg-wrUkqEZcxg mZN'RUz-mPs nMNLC'V YveJOb/FxTlpwa,jvRATDnY_YHkizQFM wJfH/VcYXqUWP'u-VcYXqUWP'u vResQTh?YveJOb wrUkqEZcxg-wJfH wJfH:YveJOb?rW'sGEpz_oIkwYu,YHkizQFM;FxTlpwa FNt-zdo kcCjPMapPE?FxTlpwa YHkizQFM:aE'kIYZTBt FxTlpwa zdo!jvRATDnY_YHkizQFM_jvRATDnY.mZN'RUz/vResQTh WDZ,mZN'RUz!VcYXqUWP'u_jvRATDnY mZN'RUz oIkwYu wJfH mZN'RUz.FxTlpwa jvRATDnY-mZN'RUz jvRATDnY,wrUkqEZcxg vResQTh VcYXqUWP'u wJfH_FxTlpwa;WDZ/jvRATDnY FxTlpwa YveJOb?zdo?zdo wrUkqEZcxg:wrUkqEZcxg;zdo,jvRATDnY kcCjPMapPE VcYXqUWP'u.rW'sGEpz rW'sGEpz zdo wrUkqEZcxg!nMNLC'V zdo YHkizQFM!wJfH mPs,oIkwYu mZN'RUz kcCjPMapPE FxTlpwa vResQTh mPs-kcCjPMapPE:aE'kIYZTBt/wrUkqEZcxg-nMNLC'V/rW'sGEpz?VcYXqUWP'u.YveJOb_wrUkqEZcxg:oIkwYu!oIkwYu vResQTh;YHkizQFM FxTlpwa.zdo!FxTlpwa YveJOb aE'kIYZTBt kcCjPMapPE_nMNLC'V-rW'sGEpz!rW'sGEpz wrUkqEZcxg_aE'kIYZTBt_oIkwYu:VcYXqUWP'u.wrUkqEZcxg wrUkqEZcxg YHkizQFM rW'sGEpz_zdo YveJOb:wrUkqEZcxg zdo,vResQTh wJfH-YHkizQFM.WDZ_jvRATDnY,rW'sGEpz vResQTh hkQn.mZN'RUz zdo wrUkqEZcxg!rW'sGEpz hkQn,oIkwYu.VcYXqUWP'u YHkizQFM FxTlpwa mZN'RUz;aE'kIYZTBt YveJOb_kcCjPMapPE FxTlpwa/rW'sGEpz FxTlpwa wrUkqEZcxg_nMNLC'V jvRATDnY,oIkwYu,oIkwYu aE'kIYZTBt:wrUkqEZcxg/jvRATDnY FNt!VcYXqUWP'u rW'sGEpz wJfH,vResQTh?vResQTh wJfH jvRATDnY mZN'RUz_zdo VcYXqUWP'u.wJfH vResQTh_rW'sGEpz oIkwYu zdo kcCjPMapPE VcYXqUWP'u jvRATDnY;FxTlpwa/YHkizQFM?nMNLC'V?wrUkqEZcxg,kcCjPMapPE rW'sGEpz rW'sGEpz oIkwYu?aE'kIYZTBt!VcYXqUWP'u wJfH wrUkqEZcxg oIkwYu/FxTlpwa?YveJOb mZN'RUz YveJOb/aE'kIYZTBt.YHkizQFM?kcCjPMapPE hkQn FxTlpwa,zdo oIkwYu!rW'sGEpz vResQTh:rW'sGEpz FxTlpwa YveJOb?oIkwYu nMNLC'V.VcYXqUWP'u,vResQTh YHkizQFM:WDZ kcCjPMapPE.VcYXqUWP'u oIkwYu wrUkqEZcxg YHkizQFM hkQn WDZ vResQTh zdo FxTlpwa wJfH-VcYXqUWP'u-VcYXqUWP'u/YveJOb aE'kIYZTBt,VcYXqUWP'u?zdo YveJOb-YveJOb:hkQn?wrUkqEZcxg vResQTh \n" +
                "meCzFCUDvA!REf iXNweYu_sVXjG XGbjlaJV sVXjG BKqGRUpeA meCzFCUDvA;dmHcj MNSbDrVZtL_UEqbLmGSa sVXjG,xOSCF';UEqbLmGSa:dmHcj dmHcj!meCzFCUDvA.sVXjG meCzFCUDvA.meCzFCUDvA TAGEOYoB REf UEqbLmGSa meCzFCUDvA,cQwhO TGCEssQw xOSCF'?dmHcj;UEqbLmGSa meCzFCUDvA!TAGEOYoB;dmHcj TAGEOYoB meCzFCUDvA REf.sVXjG TGCEssQw!xOSCF'?XGbjlaJV-xOSCF' dhN_TGCEssQw TGCEssQw cQwhO.meCzFCUDvA meCzFCUDvA!TAGEOYoB:xOSCF':REf dmHcj dmHcj-dhN TAGEOYoB-sVXjG!dmHcj dmHcj TAGEOYoB_sVXjG:MNSbDrVZtL xOSCF' meCzFCUDvA,MNSbDrVZtL TAGEOYoB.sVXjG.UEqbLmGSa:TAGEOYoB!sVXjG dmHcj meCzFCUDvA?REf:xOSCF' MNSbDrVZtL sVXjG TGCEssQw_dmHcj-cQwhO:TGCEssQw.BKqGRUpeA xOSCF'/xOSCF' sVXjG!TGCEssQw meCzFCUDvA.meCzFCUDvA,xOSCF' TAGEOYoB sVXjG xOSCF' sVXjG meCzFCUDvA meCzFCUDvA_MNSbDrVZtL!xOSCF' sVXjG!dmHcj?cQwhO-dmHcj XGbjlaJV TGCEssQw XGbjlaJV meCzFCUDvA_XGbjlaJV;TGCEssQw;dmHcj dhN TAGEOYoB,meCzFCUDvA meCzFCUDvA TGCEssQw xOSCF':meCzFCUDvA;UEqbLmGSa:iXNweYu_XGbjlaJV:sVXjG,iXNweYu REf!REf!sVXjG_TAGEOYoB dmHcj TAGEOYoB;cQwhO?dhN meCzFCUDvA,MNSbDrVZtL,REf dmHcj TAGEOYoB sVXjG,xOSCF' UEqbLmGSa dmHcj TGCEssQw;UEqbLmGSa sVXjG REf TGCEssQw meCzFCUDvA TAGEOYoB cQwhO.KdB XGbjlaJV TGCEssQw xOSCF' MNSbDrVZtL XGbjlaJV,xOSCF' XGbjlaJV sVXjG/cQwhO dmHcj TAGEOYoB cQwhO?REf!REf;XGbjlaJV UEqbLmGSa-TAGEOYoB iXNweYu REf,cQwhO:sVXjG dmHcj xOSCF'-dhN XGbjlaJV!meCzFCUDvA,meCzFCUDvA TAGEOYoB;TAGEOYoB/TGCEssQw TAGEOYoB TAGEOYoB REf;dmHcj-meCzFCUDvA.REf-xOSCF',meCzFCUDvA,xOSCF'-xOSCF';meCzFCUDvA dmHcj sVXjG cQwhO,cQwhO xOSCF' dmHcj;UEqbLmGSa iXNweYu meCzFCUDvA meCzFCUDvA.dmHcj sVXjG/XGbjlaJV meCzFCUDvA,MNSbDrVZtL sVXjG xOSCF' TGCEssQw:TAGEOYoB sVXjG;TAGEOYoB:\n" +
                "ORVtVYyFN,ORVtVYyFN!ORVtVYyFN ORVtVYyFN ORVtVYyFN!ORVtVYyFN ORVtVYyFN ORVtVYyFN/ORVtVYyFN!ORVtVYyFN:ORVtVYyFN ORVtVYyFN ORVtVYyFN ORVtVYyFN ORVtVYyFN!ORVtVYyFN ORVtVYyFN ORVtVYyFN_\n" +
                "xQz,HfEnK WpmEr-sEMMMYKOOA?tKcxRE.ZJBzAQHWxd;WpmEr HfEnK?xQz NOXKNcyd ZJBzAQHWxd.YZxofLc xQz-MHv;aXRS/fnvxkLdQEe fnvxkLdQEe rFbqSAYCi ZJBzAQHWxd:rFbqSAYCi.a'fCeo-WbHXe rFbqSAYCi;rFbqSAYCi HfEnK_NOXKNcyd_rFbqSAYCi NOXKNcyd!a'fCeo_aXRS-MHv:VKx?cNn-sEMMMYKOOA cNn xQz:MHv ZJBzAQHWxd wvECUUnC_HfEnK?WpmEr NOXKNcyd WpmEr VKx rFbqSAYCi WpmEr_WpmEr aXRS NOXKNcyd HfEnK.HfEnK_HfEnK:cNn VKx;xQz:HfEnK_tKcxRE:ZJBzAQHWxd-xQz ZJBzAQHWxd a'fCeo.VKx.VKx WbHXe ryHm ZJBzAQHWxd MHv/WbHXe,VKx_rFbqSAYCi xQz sEMMMYKOOA aXRS tKcxRE.rFbqSAYCi?xQz-WpmEr rFbqSAYCi sEMMMYKOOA:xQz MHv WpmEr ZJBzAQHWxd!wvECUUnC,cNn!aXRS a'fCeo cNn YZxofLc rFbqSAYCi/cNn:HfEnK HfEnK sEMMMYKOOA:WbHXe_HfEnK/MHv NGSw-NOXKNcyd:MHv sEMMMYKOOA:YZxofLc/ZJBzAQHWxd aXRS;fnvxkLdQEe HfEnK cNn xQz/MHv_YZxofLc_aXRS/a'fCeo rFbqSAYCi?fnvxkLdQEe a'fCeo NOXKNcyd!fnvxkLdQEe sEMMMYKOOA WbHXe xQz WpmEr:WpmEr!HfEnK WpmEr WbHXe cNn,a'fCeo VKx rFbqSAYCi fnvxkLdQEe rFbqSAYCi WpmEr rFbqSAYCi aXRS_aXRS NOXKNcyd:YZxofLc a'fCeo?xQz cNn,ryHm WpmEr.fnvxkLdQEe,WpmEr.ZJBzAQHWxd?MHv?fnvxkLdQEe NOXKNcyd a'fCeo WbHXe!rFbqSAYCi ZJBzAQHWxd?sEMMMYKOOA NGSw xQz xQz rFbqSAYCi:ZJBzAQHWxd-xQz ZJBzAQHWxd YZxofLc/a'fCeo cNn aoqUd VKx xQz WpmEr WpmEr ryHm HfEnK MHv!WpmEr cNn/aXRS_xQz WpmEr rFbqSAYCi WpmEr?fnvxkLdQEe_xQz,cNn HfEnK WbHXe,tKcxRE aXRS,NOXKNcyd:xQz WpmEr aXRS aXRS WbHXe HfEnK fnvxkLdQEe_xQz_xQz;cNn-xQz rFbqSAYCi?xQz?a'fCeo-wvECUUnC fnvxkLdQEe fnvxkLdQEe-a'fCeo cNn ZJBzAQHWxd!fnvxkLdQEe aoqUd?aXRS a'fCeo aXRS;rFbqSAYCi NOXKNcyd:WpmEr!ZJBzAQHWxd.rFbqSAYCi_VKx sEMMMYKOOA!fnvxkLdQEe?VKx cNn cNn a'fCeo rFbqSAYCi a'fCeo a'fCeo;a'fCeo WpmEr rFbqSAYCi;WpmEr?xQz cNn fnvxkLdQEe,sEMMMYKOOA xQz_WpmEr;VKx rFbqSAYCi sEMMMYKOOA cNn;YZxofLc WpmEr VKx,a'fCeo-cNn HfEnK/cNn?WpmEr VKx WpmEr WbHXe;\n" +
                "qEA.ayUlnlW!DfhRMervz_KKgYvjed;KKgYvjed VsUkOL DfhRMervz_BWfZV FbiZ MTz/BWfZV-mbsxInK RthOxv FbiZ FbiZ.VsUkOL.mbsxInK-ayUlnlW BWfZV DfhRMervz KKgYvjed RthOxv_mbsxInK/BWfZV qEA mbsxInK?LAJ_tKcEBm'VJv?VsUkOL tKcEBm'VJv DfhRMervz gOstUeqOz,MTz.KKgYvjed_VsUkOL PzKbFJ/VsUkOL mbsxInK qEA DfhRMervz/PzKbFJ/qEA PzKbFJ/PzKbFJ-ayUlnlW-mbsxInK:BWfZV;DfhRMervz VsUkOL;FbiZ VsUkOL.VsUkOL KKgYvjed RthOxv_yanLo KKgYvjed!DfhRMervz FbiZ BWfZV;BWfZV mbsxInK tKcEBm'VJv?FbiZ DfhRMervz;rqzwpg,rqzwpg_FbiZ,DfhRMervz ayUlnlW-crJg,BWfZV PzKbFJ/ayUlnlW rqzwpg!FbiZ,FbiZ tKcEBm'VJv qEA qEA.KKgYvjed Kzwv vqWeoM FbiZ!ayUlnlW RthOxv;VsUkOL crJg?VsUkOL;ayUlnlW KKgYvjed/BWfZV FbiZ MTz/BWfZV/DfhRMervz_yanLo-DfhRMervz PzKbFJ mbsxInK_tKcEBm'VJv mbsxInK;ayUlnlW?FbiZ yanLo;MTz LAJ?FbiZ,yanLo?FbiZ/yanLo tKcEBm'VJv Kzwv?yanLo VsUkOL DfhRMervz,DfhRMervz DfhRMervz,KKgYvjed BWfZV qEA VsUkOL RthOxv mbsxInK-Kzwv;qEA BWfZV/RthOxv!VsUkOL/RthOxv mbsxInK VsUkOL:qEA_DfhRMervz,tKcEBm'VJv/MTz BWfZV rqzwpg.VsUkOL;LAJ yanLo BWfZV FbiZ mbsxInK KKgYvjed RthOxv;RthOxv,mbsxInK:RthOxv-DfhRMervz:mbsxInK_PzKbFJ MTz-KKgYvjed/BWfZV yanLo DfhRMervz.DfhRMervz FbiZ_BWfZV BWfZV DfhRMervz Kzwv Kzwv!KKgYvjed FbiZ rqzwpg yanLo PzKbFJ:rqzwpg BWfZV!VsUkOL BWfZV:KKgYvjed yanLo?VsUkOL FbiZ;MTz-BWfZV!BWfZV yanLo?KKgYvjed PzKbFJ BWfZV MTz RthOxv;DfhRMervz PzKbFJ:MTz rqzwpg RthOxv yanLo;mbsxInK:KKgYvjed?qEA;VsUkOL rqzwpg mbsxInK KKgYvjed mbsxInK,FbiZ BWfZV;CdxT-rqzwpg/VsUkOL Kzwv;qEA!DfhRMervz?qEA ayUlnlW!qEA-mbsxInK MTz DfhRMervz BWfZV.BWfZV!RthOxv:DfhRMervz:LAJ crJg!KKgYvjed;LAJ-PzKbFJ RthOxv BWfZV KKgYvjed DfhRMervz ayUlnlW;ayUlnlW/FbiZ FbiZ DfhRMervz VsUkOL VsUkOL VsUkOL_DfhRMervz rqzwpg MTz FbiZ crJg Kzwv BWfZV mbsxInK?tKcEBm'VJv.qEA ayUlnlW?mbsxInK LAJ;VsUkOL!RthOxv ayUlnlW MTz LAJ PzKbFJ?VsUkOL,yanLo PzKbFJ/BWfZV!FbiZ.ayUlnlW rqzwpg Kzwv crJg mbsxInK crJg:DfhRMervz/mbsxInK mbsxInK MTz-Kzwv crJg;yanLo FbiZ Kzwv BWfZV CdxT_BWfZV RthOxv FbiZ-MTz mbsxInK:Kzwv DfhRMervz FbiZ PzKbFJ_crJg mbsxInK;CdxT qEA/VsUkOL tKcEBm'VJv.rqzwpg.PzKbFJ KKgYvjed rqzwpg mbsxInK yanLo qEA.PzKbFJ tKcEBm'VJv KKgYvjed KKgYvjed!qEA FbiZ rqzwpg-RthOxv FbiZ:FbiZ MTz VsUkOL:MTz qEA VsUkOL qEA yanLo rqzwpg \n" +
                "QYFbsMmGUP GpQRobar'Q QYFbsMmGUP,J'xOsGL_GpQRobar'Q J'xOsGL?MudyKF:CBhuehlm MkHklsB MudyKF/MudyKF;CBhuehlm-kja_CBhuehlm-GpfHTncRn_sFALNkh WsMowrjQ GpfHTncRn kja MkHklsB GpQRobar'Q sFALNkh GpfHTncRn FgVlup:MudyKF HCQNt.FgVlup kja,MudyKF WsMowrjQ,J'xOsGL:MkHklsB FgVlup!crIHPPZ J'xOsGL WsMowrjQ kja crIHPPZ;kja HCQNt CBhuehlm.sFALNkh GpQRobar'Q WsMowrjQ.MudyKF:J'xOsGL HCQNt;crIHPPZ IIgiiZznda,MudyKF kja?GpQRobar'Q MkHklsB-CBhuehlm yRvKCUn GpfHTncRn IIgiiZznda IIgiiZznda?HCQNt GpQRobar'Q!JWasErOs;MudyKF:GpQRobar'Q IIgiiZznda GpfHTncRn JWasErOs kja,QYFbsMmGUP MudyKF FgVlup.kja?WsMowrjQ LrzBm?kja,GpfHTncRn crIHPPZ.EslpPVUI!CBhuehlm?JWasErOs,MkHklsB vTeZnfyP sFALNkh crIHPPZ.crIHPPZ kja/MkHklsB GpfHTncRn?GpQRobar'Q crIHPPZ CBhuehlm kja sFALNkh;WsMowrjQ CBhuehlm saRn/JWasErOs GpfHTncRn;J'xOsGL MudyKF WsMowrjQ,WsMowrjQ GpQRobar'Q,JWasErOs?HCQNt.QYFbsMmGUP.crIHPPZ J'xOsGL FgVlup.HCQNt GpQRobar'Q JWasErOs_WsMowrjQ:CBhuehlm:crIHPPZ GpfHTncRn/QYFbsMmGUP_FgVlup/WsMowrjQ,HCQNt;GpfHTncRn_QYFbsMmGUP WsMowrjQ crIHPPZ/GpfHTncRn CBhuehlm;JWasErOs/HCQNt?J'xOsGL GpfHTncRn_kja.CBhuehlm:FgVlup.IIgiiZznda GpfHTncRn!J'xOsGL GpQRobar'Q HCQNt GpQRobar'Q,IIgiiZznda/HCQNt!CBhuehlm kja FgVlup_JWasErOs,IIgiiZznda LrzBm/crIHPPZ GpfHTncRn MkHklsB?WsMowrjQ,crIHPPZ crIHPPZ kja crIHPPZ saRn-EslpPVUI GpfHTncRn kja?FgVlup IIgiiZznda CBhuehlm!WsMowrjQ,MkHklsB!kja_J'xOsGL crIHPPZ_MudyKF kja.vTeZnfyP;J'xOsGL crIHPPZ CBhuehlm crIHPPZ HCQNt MudyKF;GpfHTncRn:FgVlup!FgVlup:MudyKF crIHPPZ/HCQNt_CBhuehlm?FgVlup J'xOsGL CBhuehlm GpQRobar'Q sFALNkh HCQNt kja IIgiiZznda;CBhuehlm GpfHTncRn FgVlup-CBhuehlm!vTeZnfyP-sFALNkh J'xOsGL-IIgiiZznda-HCQNt,IIgiiZznda:QYFbsMmGUP MkHklsB?EslpPVUI GpfHTncRn_FgVlup/MudyKF sFALNkh MkHklsB?kja HCQNt kja WsMowrjQ?crIHPPZ CBhuehlm-sFALNkh/J'xOsGL J'xOsGL WsMowrjQ?vTeZnfyP crIHPPZ:kja?FgVlup,sFALNkh GpfHTncRn_GpfHTncRn GpfHTncRn GpfHTncRn GpQRobar'Q-sFALNkh!vTeZnfyP/kja kja crIHPPZ?CBhuehlm WsMowrjQ JWasErOs HCQNt CBhuehlm:MudyKF-LrzBm/GpfHTncRn yRvKCUn kja:J'xOsGL JWasErOs?GpQRobar'Q FgVlup?GpfHTncRn,GpfHTncRn;HCQNt;MkHklsB:kja-yRvKCUn/MkHklsB:EslpPVUI!GpQRobar'Q/EslpPVUI GpfHTncRn sFALNkh;GpQRobar'Q GpQRobar'Q kja_FgVlup crIHPPZ:IIgiiZznda crIHPPZ CBhuehlm:MkHklsB vTeZnfyP;FgVlup-WsMowrjQ MudyKF J'xOsGL MkHklsB J'xOsGL,kja!GpfHTncRn?MkHklsB QYFbsMmGUP_MudyKF crIHPPZ MkHklsB sFALNkh:HCQNt kja;yRvKCUn QYFbsMmGUP J'xOsGL:FgVlup sFALNkh FgVlup EslpPVUI MkHklsB sFALNkh MudyKF-MkHklsB?yRvKCUn;FgVlup:kja yRvKCUn JWasErOs;\n" +
                "ppWa wDVhOzylL wDVhOzylL wDVhOzylL wDVhOzylL bPHeb:wDVhOzylL?wDVhOzylL wDVhOzylL wDVhOzylL,ppWa wDVhOzylL wDVhOzylL wDVhOzylL bPHeb;bPHeb bPHeb wDVhOzylL bPHeb-wDVhOzylL,wDVhOzylL wDVhOzylL ppWa bPHeb,wDVhOzylL bPHeb.bPHeb_wDVhOzylL,wDVhOzylL bPHeb:bPHeb?ppWa wDVhOzylL/wDVhOzylL ppWa bPHeb bPHeb JEUU bPHeb/\n" +
                "NtbHrDosQ HJNffW KbQeBTCL DMzYS JAqwoTA sCATFzsiR' kuxbQl-asMoGZgg,NtbHrDosQ/pfRl;XQNc asMoGZgg;JAqwoTA DMzYS:UjJH NAYDC/pfRl/NtbHrDosQ XZtTMMbhGr/pfRl qJjWl.XZtTMMbhGr DMzYS JAqwoTA qJjWl NAYDC DMzYS DMzYS DMzYS NtbHrDosQ XQNc NtbHrDosQ?DMzYS asMoGZgg!XZtTMMbhGr NAYDC pfRl asMoGZgg XZtTMMbhGr!pfRl;NAYDC sCATFzsiR' TVFQDzGjMo!kuxbQl UjJH-XZtTMMbhGr DMzYS NtbHrDosQ,hbHtl/DMzYS NtbHrDosQ;qJjWl.UUKhtnffD?asMoGZgg NAYDC NtbHrDosQ DMzYS hbHtl NAYDC:XZtTMMbhGr/UUKhtnffD DMzYS.DMzYS XZtTMMbhGr.NAYDC-sCATFzsiR' pfRl aCe JAqwoTA/asMoGZgg NtbHrDosQ asMoGZgg DMzYS/pfRl XZtTMMbhGr pfRl XQNc!sCATFzsiR'/XQNc NtbHrDosQ sCATFzsiR';JAqwoTA;UUKhtnffD?kuxbQl.sCATFzsiR' asMoGZgg NtbHrDosQ UUKhtnffD_NtbHrDosQ NtbHrDosQ/XQNc_UUKhtnffD NtbHrDosQ asMoGZgg hbHtl XZtTMMbhGr asMoGZgg:NtbHrDosQ/sCATFzsiR' pfRl/NtbHrDosQ.qJjWl_NAYDC sCATFzsiR'.sCATFzsiR'!KbQeBTCL?sCATFzsiR' NAYDC-XQNc NtbHrDosQ:pfRl?XZtTMMbhGr NAYDC;asMoGZgg_KbQeBTCL hbHtl asMoGZgg;sCATFzsiR' qJjWl asMoGZgg JAqwoTA NAYDC NtbHrDosQ qJjWl sCATFzsiR' DMzYS/NtbHrDosQ qJjWl pfRl-asMoGZgg UUKhtnffD KbQeBTCL.XZtTMMbhGr_NtbHrDosQ_pfRl asMoGZgg yuqmKF pfRl UUKhtnffD asMoGZgg XZtTMMbhGr,qJjWl DMzYS_sCATFzsiR' NtbHrDosQ?asMoGZgg/DMzYS:NAYDC sCATFzsiR' NAYDC TVFQDzGjMo?asMoGZgg.pfRl DMzYS.sCATFzsiR' pfRl:aCe_sCATFzsiR' XQNc NAYDC sCATFzsiR' sCATFzsiR'-KbQeBTCL NAYDC sCATFzsiR' NAYDC!pfRl yuqmKF NAYDC kuxbQl asMoGZgg!hbHtl!kuxbQl-NtbHrDosQ sCATFzsiR' qJjWl NAYDC XQNc NAYDC sCATFzsiR' aCe XQNc,XQNc NtbHrDosQ DMzYS DMzYS qJjWl pfRl JAqwoTA;qJjWl qJjWl NAYDC,NAYDC!XQNc hbHtl/pfRl!kuxbQl_sCATFzsiR';qJjWl:XZtTMMbhGr XZtTMMbhGr;qJjWl qJjWl sCATFzsiR' sCATFzsiR'.pfRl.XQNc,NtbHrDosQ pfRl pfRl asMoGZgg!asMoGZgg sCATFzsiR';XZtTMMbhGr-DMzYS pfRl-pfRl qJjWl?sCATFzsiR' DMzYS.XQNc-JAqwoTA NtbHrDosQ NAYDC,asMoGZgg,qJjWl NtbHrDosQ-NAYDC/JAqwoTA_XQNc:kuxbQl NtbHrDosQ asMoGZgg asMoGZgg XQNc?DMzYS,JAqwoTA pfRl DMzYS NtbHrDosQ_pfRl JAqwoTA DMzYS-asMoGZgg XQNc:yuqmKF pfRl sCATFzsiR';NtbHrDosQ NAYDC!asMoGZgg kuxbQl XZtTMMbhGr asMoGZgg!KbQeBTCL!hbHtl DMzYS pfRl XZtTMMbhGr pfRl?HJNffW asMoGZgg HJNffW NAYDC pfRl UUKhtnffD asMoGZgg-";
        assertEquals(Arrays.asList("pfrl", "asmogzgg", "ntbhrdosq"), TopWords.top3(input));
    }
}


//    Write a function that, given a string of text (possibly with punctuation and line-breaks),
//    returns an array of the top-3 most occurring words, in descending order of the number of occurrences.
//        Assumptions:
//
//        A word is a string of letters (A to Z) optionally containing one or more apostrophes
//        (') in ASCII. (No need to handle fancy punctuation.)
//        Matches should be case-insensitive, and the words in the result should be lowercased.
//        Ties may be broken arbitrarily.
//        If a text contains fewer than three unique words, then either the top-2 or top-1 words
//        should be returned, or an empty array if a text contains no words.
//
//        Examples:
//
//        top_3_words("In a village of La Mancha, the name of which I have no desire to call to
//        mind, there lived not long since one of those gentlemen that keep a lance
//        in the lance-rack, an old buckler, a lean hack, and a greyhound for
//        coursing. An olla of rather more beef than mutton, a salad on most
//        nights, scraps on Saturdays, lentils on Fridays, and a pigeon or so extra
//        on Sundays, made away with three-quarters of his income.")
//        # => ["a", "of", "on"]
//
//        top_3_words("e e e e DDD ddd DdD: ddd ddd aa aA Aa, bb cc cC e e e")
//        # => ["e", "ddd", "aa"]
//
//        top_3_words("  //wont won't won't")
//        # => ["won't", "wont"]
//
//        For java users, the calls will actually be in the form: TopWords.top3(String s),
//        expecting you to return a List<String>.
//        Bonus points (not really, but just for fun):
//
//        Avoid creating an array whose memory footprint is roughly as big as the input text.
//        Avoid sorting the entire array of unique words.

