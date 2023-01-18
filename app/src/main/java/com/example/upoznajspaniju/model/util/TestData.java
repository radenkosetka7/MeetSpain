package com.example.upoznajspaniju.model.util;

import static com.example.upoznajspaniju.MainActivity.dbHelper;

import com.example.upoznajspaniju.model.entities.Atrakcija;
import com.example.upoznajspaniju.model.entities.Drzava;
import com.example.upoznajspaniju.model.entities.Grad;

public class TestData {

    public TestData()
    {
        insertGradovi();
        insertAtrakcija();
    }

    public void insertGradovi()
    {
        dbHelper.insertGrad(new Grad("Madrid","Madrid (šp. Madrid) je glavni grad Kraljevine Španije i autonomne pokrajine Madrid." +"\n"+
                "Region (Madrid sa okolinom) se sa oko 5 miliona stanovnika ubraja u najveće evropske metropole. Sam grad Madrid (bez predgrađa) je sa svojih 3,1 milion stanovnika, posle Londona i Berlina, treći najveći grad u Evropskoj uniji. " +'\n'+
                "Madrid je, kako od nacionalnog, tako i od internacionalnog značaja za trgovinu i finansije i politički je i kulturni centar Španije. Glavni grad je središte španske uprave, pa osim kralja, u njemu žive katolički biskup i važni pripadnici vlade i vojske. Ovde je smešteno šest univerziteta, razne visoke škole, mnoga pozorišta, muzeji i druge kulturne ustanove.","NWBtMK3GsV0",40.416775,-3.703790));

        dbHelper.insertGrad(new Grad("Barcelona","Barselona (kat. Barcelona) glavni je grad autonomne zajednice Katalonije i istoimene pokrajine Barselona na severoistoku Španije. Nalazi se na obalama Mediterana na nekih 120km od Pirineja i granice sa Francuskom, u dolini koja je okružena morem na istoku, planinskim vencom Sijera de Koljserola na zapadu, na jugu rekom Ljobregat, a na severu rekom Besos. "+'\n'+
                "Prema izveštaju španskog Zavoda za statistiku iz 2006. godine, sam grad Barselona broji 1.605.602 stanovnika. Barselona sa okolinom broji 3.161.081 stanovnika i ima površinu od 633 km². " + '\n' +
                "Barselona je jedan od glavnih evropskih ekonomskih centara, sa jednom od glavnih mediteranskih luka a aerodrom u Barseloni je drugi po veličini u Španiji. Grad je takođe i bitna turistička destinacija i ima veoma bogato kulturno i istorijsko nasleđe, od kojih se posebno ističu arhitektonska dela Antonija Gaudija i Ljuisa Domeneka i Muntanera od kojih je UNESKO mnoge proglasio svetskom baštinom. " + '\n' +
                "Barselona je bila domaćin važnim svetskim događajima koji su doprineli tome da postane veoma poznat i posećen grad. Najbitniji su Svetska izložba 1888. i 1929. godine, Letnje olimpijske igre 1992. godine, i Forum Kultura 2004. ","uOzwwZxjPRs",41.385063,2.173404));

        dbHelper.insertGrad(new Grad("Malaga","Málaga je grad i luka u španjolskoj autonomnoj zajednici Andaluziji, na obali Sredozemnog mora, u regiji Costa del Sol. Godine 2007. grad je imao 561.250 stanovnika, čime je drugi po brojnosti u Andaluziji (iza Seville), a šesti u Španjolskoj. " +'\n' +
                "Málaga je udaljena 198 km od Seville, a 544 km od Madrida. Glavne ekonomske djelatnosti su trgovina i turizam. " + '\n' +
                "U Málagi su rođeni slavni Pablo Picasso i Antonio Banderas. Grad je poznat po vinu i po sportskim klubovima Málaga CF (nogomet) i CB Unicaja Malaga (košarka). ","W-Wd5bl5b5s",36.721275,-4.421399));

        dbHelper.insertGrad(new Grad("Valencia","Valensija (šp. Valencia; kat. València) je glavni grad Valensijske autonomne zajednice, kao i pokrajine Valensija. " + '\n' +
                "Valensija (šp. Valencia; kat. València) je glavni grad Valensijske autonomne zajednice, kao i pokrajine Valensija. " + '\n' +
                "Ovo je grad sa dubokom istorijskom tradicijom, pogodnom mediteranskom klimom i brojnim znamenitostima i festivalima. ","NmsFT_OdDlU",39.546412,-0.496433));

        dbHelper.insertGrad(new Grad("Sevilla","Sevilla je privredni, kulturni i finansijski centar južne Španije glavni grad autonomne pokrajine Andaluzije i glavni grad istoimene provincije. Smještena je na obalama rijeke Gvadalkivir na 7 m nadmorske visine. Prema nacionalnom institutu za statistiku (INE) u Sevilji je 2010. godine živjelo 704.198 stanovnika dok je u metropolitanskom području grada živjelo 1.508.605 stanovnika čime se Sevilja svrstala kao 4. najveći grad u Španiji. ","9-PCsRiv6Us",37.38863,-5.99534));

        dbHelper.insertGrad(new Grad("Granada","Granada (šp. Granada) je glavni grad pokrajine Granada u autonomnoj zajednici Andaluzija u južnoj Španiji. Takođe podrazumeva i teritoriju nekadašnje mavarske kraljevine, Taife Granada. "+ '\n'+
                "Zastava Granade sastoji se od dve jednake vertikalne pruge. Onaj deo zastave koji je prikačen za jarbol, crvene je boje, dok je drugi deo zelen. U samom centru se nalazi grb. " +
                '\n' + "Unutrašnjost službenog grba Granade sastoji se od tri dela. Na gornjoj polovini unutrašnjosti grba prikazani su Katolički kraljevi sa svojim krunama, Fernando i Izabela, kako u carskim odorama sede na svojim tronovima. Kralj Fernando drži mač u svojoj desnoj ruci, dok kraljica Izabela drži žezlo. Donji deo je podeljen na dva bloka-na levom je prikazana Kula Bdenja (Torre de la Vela), sa koje se vijori crveno-žuta španska zastava, a na desnom se može videti otvoren plod nara u svojim prirodnim bojama. Ceo unutrašnji deo okružen je sa četiri dvorca, šest lavova okrenutih prema unutra i sa dve tvrđave sa kojih se vijore zastave Španije. Oko njih je obavijena uska traka, dok se na grbu nalazi sjajna zlatna kruna. ","ZZ4tgNkNmtY",37.177338,-3.598557));

        dbHelper.insertGrad(new Grad("Bilbao","Unutrašnjost službenog grba Granade sastoji se od tri dela. Na gornjoj polovini unutrašnjosti grba prikazani su Katolički kraljevi sa svojim krunama, Fernando i Izabela, kako u carskim odorama sede na svojim tronovima. Kralj Fernando drži mač u svojoj desnoj ruci, dok kraljica Izabela drži žezlo. Donji deo je podeljen na dva bloka-na levom je prikazana Kula Bdenja (Torre de la Vela), sa koje se vijori crveno-žuta španska zastava, a na desnom se može videti otvoren plod nara u svojim prirodnim bojama. Ceo unutrašnji deo okružen je sa četiri dvorca, šest lavova okrenutih prema unutra i sa dve tvrđave sa kojih se vijore zastave Španije. Oko njih je obavijena uska traka, dok se na grbu nalazi sjajna zlatna kruna. ","YYmwsu_03g8",43.263002,-2.935004));

        dbHelper.insertGrad(new Grad("Cadiz","Kadiz (šp. Cádiz) je glavni grad istoimene pokrajine Kadiz u španskoj autonomnoj zajednici Andaluzija, i gradski centar Zaliva Kadiz.[1] Treći je po veličini grad Andaluzije i jedan od najaktivnijih gradova Andaluzije u ekonomskom i industrijskom smislu. Osim toga, zajedno sa opštinama Čiklana de la Frontera, Herez de la Frontera, Puerto de Santa Marija, Puerto Real, Rota i San Fernando čini zajednicu opština Zaliva Kadiz. " + '\n'+
                "Grad je od nesumnjivo velikog turističkog značaja zbog svoje duge i značajne istorije koja traje više od 3.100 godina.","iiuPtJbmARM",36.529744, -6.292898));

        dbHelper.insertGrad(new Grad("Cordoba","Kordoba ili Kordova (šp. Córdoba) grad je u autonomnoj zajednici Andaluziji u Španiji, i glavni grad pokrajine Kordoba. Nalazi se na reci Gvadalkivir (šp. Guadalquivir), u podnožju planinskog venca Sijera Morene (šp. Sierra Morena). " + '\n'+
                "Kordoba je do danas sačuvala karakterističnu mavarsku urbanu arhitekturu s brojnim građevinama sačuvanim iz doba arapske vlasti. " + '\n' +
                "Velika Abd-ar-Rahmanova džamija, građena od 785, jedan je od najveličanstvenijih spomenika islamske umetnosti.","Us3hTNxtQ18",37.884581,-4.776014));

        dbHelper.insertGrad(new Grad("Zaragoza","Saragosa (šp. Zaragoza) je glavni grad Autonomne zajednice Aragon i Provincije Zaragoza i historijska prijestolnica nekadašnje kraljevine Aragon. Leži se na rijeci Ebro i njenim pritokama Huerva i Gállego, u velikoj dolini sa raznovrsnim krajolicima, od pustinjskog do guste šume, livada i planina. Saragosa je peti grad po veličini u Španiji. Šire gradsko područje ima 833.455 stanovnika. Grad se nalazi na 199 metara nadmorske visine. ","hImvDVnyltY",41.652134,-0.880943));

    }

    public void insertAtrakcija()
    {
        dbHelper.insertAtrakcija(new Atrakcija("Ostaci Rimskog carstva – Segovija",
                "Rimljani su vladali Španijom više od 700 godina, a ostaci njihove vladavine sreću se širom zemlje. Možda najznačajniji je akvadukt u Segoviji u centralnoj Španiji, koji su Rimljani koristili da dovode vodu u grad. Graciozni lukovi prostiru se u dva reda dužinom od 2.950 metara. U izgradnji nije korišćen malter.",
                "https://upload.wikimedia.org/wikipedia/commons/6/63/AcueductoSegovia04.JPG",0,40.947898,-4.118136));
        dbHelper.insertAtrakcija(new Atrakcija("Trg \"Španija\" – Sevilja",
                "Veličanstveni centar Andaluzije, Sevilja, odiše lepim znamenitostima i prijatnom atmosferom. Trg \"Španija\" delo je lokalnog arhitekte Anibala Gonzalesa koji ga je dizajnirao 1929. za veliku Ibero-američku izložbu. Na njemu se nalaze kitnjaste, šarene kule i kolonade ukrašene pločicama i motivima. U centru se nalazi velika fontana i kanal kojim posetioci mogu da plove u iznajmljenim pedalinama. Ovaj trg često se pojavljivao u filmovima, među kojima je najpoznatiji \"Ratovi zvezda: Napad klonova\".",
                "https://upload.wikimedia.org/wikipedia/commons/b/b6/Plaza_de_Espa%C3%B1a_%28Sevilla%29_-_01.jpg",0,37.37569,-5.986749));
        dbHelper.insertAtrakcija(new Atrakcija("Grad umetnosti i nauke – Valensija",
                "Španija je ostavila veliki trag u istoriji arhitekture, ali isto tako čvrsto je okrenuta i prema budućnosti o čemu svedoče brojni projekti. Predivni lukovi Gugenhajm muzeja, stavili su Bilbao na evropsku mapu, ali kompleks \"Grad umetnosti i nauke\" u Valensiji, delo arhitekte Santjaga Kalatrava, predstavlja vrhunac moderne španske arhitekture. Njegove amorfne bele zgrade, praznik su čudnih uglova i neobičnih oblika, pod noćnim osvetljenjem izgledaju posebno spektakularno.",
                "https://upload.wikimedia.org/wikipedia/commons/a/a4/Hemispheric_-_Valencia%2C_Spain_-_Jan_2007.jpg",0,39.456139,-0.355431));
        dbHelper.insertAtrakcija(new Atrakcija(" Prelepe plaže – Formentera",
                "Ako se ima u vidu da Španija ima više od pet hiljada milja obale i sunčane dane većinom godine, ne čudi što je Meka za ljubitelje ležanja na plaži i kupanja. Svako može da nađe plažu po svojoj meri – od onih prenatrpanim turistima do zabačenih i divljih, dostupnih samo najupornijima. Najznačajnije i najlepše među tim netaknutim nalaze se na Balearskom ostrvu Formentera u Mediteranu do koga se stiže posle nekoliko sati provedenih u putovanju broda koji je isplovio sa kopna.",
                "https://playtravel.rs/wp-content/uploads/2016/07/2-Playa-de-Formentor-Majorka.jpg",0,38.706391,1.433527));
        dbHelper.insertAtrakcija(new Atrakcija("Pogled sa vulkana – Mount Teide Tenerife",
                "Nekoliko sati leta avionom južno od Španije dovešće vas do Kanarskih ostrva čija je blizina afričkog kontinenta doprinela drugačijem pejzažu i prirodi, uopšte, od one koju srećemo na evropskoj obali. Veći deo zemlje je vulkanskog porekla, sa strmim padinama i sivim peskom na plažama. U središtu Tenerifa, najvećeg Kanarskog ostrva, nalazi se moćni Mont Teide, snegom prekriveni vulkan, privlačan za planinare. Sa vrha vulkana pruža se spektakularan pogled na ostala Kanarska ostrva, a do njega je moguće stići uz pomoć žičare",
                "https://www.zanimacija.com/wp-content/uploads/2015/03/118.jpg",0, 28.2691,-16.6338));
        dbHelper.insertAtrakcija(new Atrakcija("Katedrala – Santjago de Kompostela",
                "Brojne gotske katedrale u Španiji svedoče o hrišćanskoj tradicije ove zemlje. Najviše očarava upravo ova u Santjagu de Komposteli, glavnom gradu španske Galicije. Reč je o ogromnoj zgradi satkanoj u baroknom vrtlogu statua i stalagmita. Najprivlačnija je zapadna strana građevine na kojoj se nalazi glavni ulaz kome se prostupa veličanstvenim četvorostrukim stepenicama. Katedralu svake godine poseti na hiljade hodočasnika.",
                "http://galerija.metropolitan.ac.rs/var/albums/GD2013-2014/AD111%20Istorija%20umetnosti%20starog%20i%20srednjeg%20veka/Romani%C4%8Dka%20arhitektura-01/Katedrala%20Santiago%20de%20Compostela%2C%20%C5%A0panija%20Barokna%20zapadna%20fasada.jpg?m=1403634310",0,42.8805,-8.54576));
        dbHelper.insertAtrakcija(new Atrakcija("Zadivljujuća priroda – Picos de Europa",
                "Priroda Španije svojom lepotom stalno iznenađuje – od peščanih plaža, preko ogromnih ravnica do planinskih venaca. Ovoga puta izdvajamo uzvišene, spektakularne vrhove, planinskog venca Picos de Europa. Ovi vrhovi, uz zelene doline i reke čine ovaj deo severne Španije savršenim za planinarenje i avanturizam. Ovaj predeo odlikuje se i bogatim životinjskim svetom, a tu živi i ugrožena vrsta mrkog medveda.",
                "https://static.independent.co.uk/s3fs-public/thumbnails/image/2018/05/25/11/istock-535541291.jpg?quality=75&width=990&auto=webp&crop=982:726,smart",0,43.189602,-4.832748));
        dbHelper.insertAtrakcija(new Atrakcija("Gaudijeva remek dela – Barselona",
                "Barselona – ponosni centar Katalonije svojim posetiocima nudi dobar noćni provod, bogat šoping i lepe plaže. Ali, njenu najveću atrakciju ipak predstavljaju zgrade koje je za sobom ostavio izvanredni arhitekta Antonio Gaudi. Najposećenija je nedovršena crkva Svete Familije, sa kulama i vretenasto čudnim oblikom. Slede stambene zgrade i Casa Mila kao i statue u parku Gueljo.",
                "https://ddl.rs/wp-content/uploads/2020/02/sagrada-familia-shutterstock_1336744421.jpg",0,41.403479,2.17441));
        dbHelper.insertAtrakcija(new Atrakcija("Alhambra – Granada",
                "Kitnjasta mavarska palata, Alhambra, jedan je od najposećenijih spomenika Španije. Kompleks palate, okružen je uređenim vrtovima i snegom prekrivenim vrhovima Sijera Nevade. Za zapanjujućom okolinom ne zaostaje ni predivna, romantična unutrašnjost koja odiše mirom i dekoracijama u, takođe, mavarskom stilu.",
                "https://cdn-imgix.headout.com/mircobrands-content/image/6c5d1b58f8e696414d74bcc4708c2e4c-Best%20Time%20to%20Visit%20Alhambra.jpg?auto=compress%2Cformat&h=573&q=75&fit=crop&ar=16%3A9&fm=webp",0, 37.1882,-3.6067));
        dbHelper.insertAtrakcija(new Atrakcija(" Bela sela – Andaluzija",
                "Bela sela, raštrkana po obroncima planina centralne Andaluzije, prkose vremenu i gravitaciji. Nekada farmerski orijentisana danas pretežno opustošena zato što se mlađe stanovinštvo neretko iz njih iseljavalo i u potrazi za poslom naseljavalo u gradovima. Uprkos toj činjenici lepotom i šarmom i dalje se ističu sela Grazalema, Vejer de la Frontera i Arcos de la Frontera.",
                "https://static.nationalgeographic.rs/Picture/36483/jpeg/najlepsa_sela_spanije_altea_850153011?ts=2021-07-18T00:10:58",0,36.751087,-5.81326));

    }


}
