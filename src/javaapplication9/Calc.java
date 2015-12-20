/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication9;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.NoSuchElementException;
/**
 *
 * @author kasyan
 */
public class Calc {
    
    
    public String resh(String st){
        LinkedList<Double> operand = new LinkedList<>();
        LinkedList<Character> operation = new LinkedList<>();
        st = st.trim();// убираем пробелы слева и справа
        if (st.length()>0){
            for(int i= 0;i<st.length();i++){
                char elem = st.charAt(i);
                if (Character.isWhitespace(elem)){ continue;}//если пробел, то пропускаем
           
                if (elem =='('){// если ( то добавляем в операции
                   
                    operation.add(elem);
                }
                else{
                    if (elem ==')'){
                        
                        try{    
                            while(operation.getLast()!='('){
                            arifmOper(operand,operation.removeLast());   
                            }
                            operation.removeLast();
                        }catch(NoSuchElementException e)
                        {   
                            return null;
                        }
                         
                    }
                    else{
                        if(isOperator(elem)){// если элемент оператор
                            try{
                            while(!operation.isEmpty() && prioritet(operation.getLast())>=prioritet(elem)){
                                arifmOper(operand, operation.removeLast());
                            }
                            operation.add(elem);
                            }catch(NoSuchElementException e)
                            {
                                return null;
                            }    
                        
                        }
                         else{
                            String operand1 = "";// если символ цифра
                            while(i <st.length()&&(Character.isDigit(st.charAt(i)) || st.charAt(i)=='.')){
                                operand1 +=st.charAt(i++);
                            }
                            --i;
                            try{
                            operand.add(Double.parseDouble(operand1));
                            }catch(NumberFormatException e){
                                return null;
                            }
                            
                        }
                        
                    }
                                   
                }
      
            }
        
            try{
            while (!operation.isEmpty()){
                arifmOper(operand, operation.removeLast());
            }
            }catch(NoSuchElementException e){
                return null;
            }catch(ArithmeticException e){
                return null;
            }
            
        }else 
        {return null;}
    
        
        //operand.get(0).toString()
      
        return round(operand.get(0));
    }
    
    static boolean isOperator(char c){
    return c=='+'||c=='-'||c=='*'||c=='/';
    }
    
    static int prioritet(char op){
        switch(op){
            case'+':
            case'-':
                return 1;
                
            case'*':
            case'/':
                return 2;
            
            default:return -1;
        }
    }
    
    void arifmOper(LinkedList<Double> operand,char operation){ 
        double second = operand.removeLast();
        double first = operand.removeLast();
        System.out.print(Double.toString(first));
        System.out.print("  "+ Character.toString(operation)+"  ");
        System.out.print(Double.toString(second)+"\n");
        
        if(operation =='+') operand.add(first+second);
        if(operation =='-') operand.add(first-second);
        if(operation =='*') operand.add(first*second);
        if(operation =='/') operand.add(first/second);
        
        
    }
    String round(double x){// Округление
     BigDecimal y = new BigDecimal(x);
        return String.valueOf(y.setScale(4, BigDecimal.ROUND_HALF_UP));
    }
    
}
