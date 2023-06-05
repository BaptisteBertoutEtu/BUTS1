import extensions.CSVFile;
class USPresident extends Program{

    void print(CSVFile csv) {
        for (int line=0; line<rowCount(csv); line++) {
            for (int column=0; column<columnCount(csv, line); column++) {
                print(getCell(csv, line, column)+"  ");
            }
            println();
        }
    }

    President newPresident(String nom, String parti, int anneeDebutMandat, int anneeFinMandat){
        President p= new President();
        p.nom=nom;
        p.parti=parti;
        p.anneeDebutMandat=anneeDebutMandat;
        p.anneeFinMandat=anneeFinMandat;
        return p;
    }

    President[] load(String nomFichier, int casesSuppl){
        President[] p=new President[int rowCount(CSVFile table)]
    }

    void algorithm() {
        print(loadCSV("USPresident.csv"));
    }

}