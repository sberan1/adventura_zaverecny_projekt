package logika;


public class PrikazPoloz implements IPrikaz{

    public final String NAZEV = "poloz"; //nazev prikazu a jeho zneni pro pouziti
    private HerniPlan plan; //instance tridy herni plan

    /**
     * Konstruktor tridy
     *
     * @param plan herni plan obsahujici batoh a mistnosti
     */
    public PrikazPoloz(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     * Co se provede po exekuci prikazu
     *
     * @param parametry počet parametrů závisí na konkrétním příkazu.
     * @return textovy retezec, ktery navrati hra
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo (Vec co vyndat z batohu), tak ....
            return "Coze mam z toho batohu jako vyndat?";
        }
        if (parametry.length > 1) {
            //pokud je vice jak jedno slovo za prikazem tak ....
            return "Jo, no super info a ted si vyber jednu vec co mam vyndat, ju?";
        }
        Vec mistni = null;
        for (var item : plan.getBatuzek().getObsah()) {
            if (parametry[0].equalsIgnoreCase(item.getNormalizedNazev())){
                mistni = item;
            }
        }
        if(plan.getBatuzek().odeberVec(parametry[0])) {
            plan.getAktualniProstor().vlozVec(mistni);
            return mistni.getNazev() + "jsi teda polozil na zem, v batohu uz to nemas";
        }
        return "to nemas v batohu, tak jak bys to chtel polozit prosimte?";
    }

    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *
     *  @return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
