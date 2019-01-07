package questao1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinaString{
    private List<String> mapeiaLetras;
    private List<String> combinacoes;
    private int retorno;
    public CombinaString(){
        mapeiaLetras = Arrays.asList("","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z");
        //string vazia na primeira posi��o por causa da indexa��o em 0 do Java        
    }

    public int numeroCombinacoes(String entrada){
        retorno = 0;
        int k = entrada.length();
        combinacoes = new ArrayList<>();
        if(k==1 && entrada!="0"){
        	combinacoes.add(mapeiaLetras.get(Integer.parseInt(entrada)));
            return 1;
        }
        
        String primeiraComb = "";
        for(int i=0;i<k;i++) {
        	char atual = entrada.charAt(i);
        	if(atual=='0') return -1; //interromper leitura da string por conta de d�gito inv�lido
    		primeiraComb += entrada.charAt(i);
        	if(i<k-1) primeiraComb += ",";
        	
        }
        combinacoes.add(primeiraComb);//por padr�o considerar a string de entrada
        retorno++;
        formarCombinacao(primeiraComb);
        if(combinacoes.size()>1) {
        	//o segundo elemento pode ou n�o ser um n�mero formado por 2 d�gitos, combinando o primeiro e segundo d�gitos da entrada
        	//caso seja, deve-se formar as combina��es tamb�m com esse elemento
        	for(int a=1;a<combinacoes.size();a++) {
        		formarCombinacao(combinacoes.get(a));
        	}
        	
        }
      
        return retorno;
    }

    public void formarCombinacao(String base){
        int j=base.indexOf(","); //a itera��o depender� do �ndice da primeira v�rgula
    	if(j==-1) return;
        String aux = base.substring(base.indexOf(",")+1);
        String poss = "";
        String digitos = "";
    	while(j<base.length()){
        	if(j==1||j==2){
        		String digAux = base.substring(0,base.indexOf(","));
        		if(digAux.length()==1) {
        			digitos = base.charAt(0)+""+aux.charAt(0);
        			poss = digitos;
        			if(aux.indexOf(",")>-1){
        				poss += aux.substring(aux.indexOf(","));
        			}
        		}
        	}else {
        		if(j+1<base.length()) {
        			digitos = base.charAt(j-1)+""+base.charAt(j+1);//caracter antes e ap�s a v�rgula
        			poss = base.substring(0,j-2) + ","+digitos;
        			if(j+2 < base.length()) poss += base.substring(j+2);
        		}
        	}
        	if(digitos.length()>0) {
        		digitos = digitos.replace(",", "");
        		int inteiro = Integer.parseInt(digitos);
        		String[] numerosComb = poss.split(",");
        		boolean digitosValidos = true;
        		for(int f=0;f<numerosComb.length;f++) {
        			int nDigitos = numerosComb[f].length();
        			digitosValidos = digitosValidos && (nDigitos<3 && nDigitos>0);
        			//a verifica��o se faz necess�ria pois pode acontecer de fserem formados
        			//n�meros com mais d�gitos que o permitido e 'engolir' outros da string original
        		}
        		
        		if(inteiro>9 && inteiro<27 && !combinacoes.contains(poss) && digitosValidos && poss.replace(",", "").length()==base.replace(",", "").length()){
        			combinacoes.add(poss);
        			retorno++;
        		}
        	}
        	String subs = base.substring(j+1);
        	if(subs.indexOf(",")==1) {
        		j = j+2;
        	}else {
        		j = j+3;//caso a pr�xima v�rgula esteja ap�s um n�mero de 2 d�gitos
        	}
        }
    
    }
    
    public List<String> getCombinacoes(){
    	return this.combinacoes;
    }
    
    public String traduzCombinacoes(String[] codificada) {
    	String retorno = "";
    	for(int a=0;a<codificada.length;a++) {
    		retorno += mapeiaLetras.get(Integer.parseInt(codificada[a]));
    	}
    	return retorno;
    }
    
    public static void main(String[] args){
        CombinaString combinaString = new CombinaString();
        System.out.println(combinaString.numeroCombinacoes("12116"));
        List<String> combinacoes = combinaString.getCombinacoes();
        for(String a : combinacoes) {
        	System.out.println(combinaString.traduzCombinacoes(a.split(",")));
        }
    }
}
