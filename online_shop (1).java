import java.io.*;
import java.lang.*;
import java.math.*;
public class online_shop
{
    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    String ds[][]=new String[10][2];
    String cart_bill[][]=new String[10][7];
    int f=0,row,tmp,qnt,count,p=0,tol_wrap=0,apply_spl_dis=0,sum=0,total_qnt=0,tot_pack=0;
    String add,price_str,wrap,dis_name="",check=" ";
    
    Double tmp_price,tot_bill=0.0,dis=0.0,cart_total=0.0;
    
    public void data_set()
    {
            /* String ds[][]= {
                            {"Product_Name ", "\tPrice"},
                            {"A ","\t\t$20"},
                            {"B","\t\t$40"},
                            {"C","\t\t$50"}
                             };*/
          ds[0][0]="Product_Name";
          ds[0][1]="\tPrice";
          ds[1][0]="A";
          ds[1][1]="\t\t"+"$"+20;
          ds[2][0]="B";
          ds[2][1]="\t\t"+"$"+40;
          ds[3][0]="C";
          ds[3][1]="\t\t"+"$"+50;
        int i,j;
        if(f==0)
            row=4;
        else
            row=tmp;
        for(i=0;i<row;i++)
        {
            for(j=0;j<2;j++)
            {
                System.out.print(ds[i][j]);
            }
            System.out.println(" ");
        }
        System.out.println("\nSelect Your Products From The Given Options");
        //System.out.println(ds[1][1].substring(3));
    }
    /*public void add_product()throws IOException
    {
        f=1;
        int i=4;
        String p_name,p_price;
        System.out.print("Enter Product Name: ");
        p_name=br.readLine();
        System.out.print("Enter Product Amount: ");
        p_price=br.readLine();
        ds[i][0]=p_name;
        ds[i][1]="\t\t"+"$"+p_price;
        ++i;
        tmp=i;
    }*/

    public void add_items_to_cart()throws IOException
    {
        int f1=0,i,g=0,c=0;

        
        System.out.println("Enter The Product Name You Wish To Add");
        System.out.print("\nProduct Name: ");
        add=br.readLine().toUpperCase();
        for(i=1;i<=row;i++)
        {
        if(add.equals(ds[i][0]))
        {
            p++;
            f1=1;
            price_str=ds[i][1];
            System.out.print("\nHow many Quantity: ");
            qnt=Integer.parseInt(br.readLine());
            System.out.print("\nShould Product Wrapped as Gift ? (Y/N): ");
            wrap=br.readLine().toUpperCase();
            if((wrap.equals("Y"))|| (wrap.equals("YES")))
            {
                 g=1;
            count= ++c * qnt;
            tol_wrap=count+tol_wrap;
            check="YES";
            }
            else
            check="NO";
            
        }
        }
        if(f1==0)
        System.out.println("\n******************Entered Prodcut is not there in Database***************");

    }

    public void cart_billing()throws IOException
    {
        add_items_to_cart();
        int i,j;
       
       for(i=(p-1);i<p;i++)
       {
        
            cart_bill[i][0]=add;//product_name
            cart_bill[i][1]=price_str.substring(2);//product_price
            cart_bill[i][2]=Integer.toString(qnt);//prodcut_qnt
            sum+=Integer.parseInt(cart_bill[i][2]);
            if(qnt>10 && qnt<15)
            {
                apply_spl_dis=1;
            }
            if(qnt>15)
            {
                String tmp11=cart_bill[i][1];
                System.out.println(tmp11.substring(1));
                apply_spl_dis=2;
                tmp_price=qnt*Double.parseDouble(tmp11.substring(1));
            }
            cart_bill[i][3]=check;
            cart_bill[i][4]=Double.toString(qnt*Double.parseDouble(price_str.substring(3)));//qnt*price
            cart_bill[i][5]=Double.toString(count*1);//wrap
            cart_bill[i][6]=Integer.toString(sum);//total qnt
            tot_bill=Double.parseDouble(cart_bill[i][4])+tot_bill;

            System.out.print("\nDo You Wish To Proceed To Cart ?(Y/N): ");
            check=br.readLine().toUpperCase();
            if((check.equals("Y"))|| (check.equals("YES")))
            {
            display();
            System.exit(0);
            }
        
        
       }

       /*System.out.println("**************Cart Deatils********************");
        for(i=0;i<p;i++)
       {
            System.out.println("Product Name: "+cart_bill[i][0]);
            System.out.println("Product Price: "+cart_bill[i][1]);
            System.out.println("Product Quantity: "+cart_bill[i][2]);
            System.out.println("Is Product is Wrapped: "+cart_bill[i][3]);
            System.out.println("Total Quantity Price: "+"$"+cart_bill[i][4]);
            
            System.out.println("Warpped Amount: "+"$"+cart_bill[i][5]);
            //sum=Integer.parseInt(cart_bill[i][6])+sum;
            System.out.println("Total Quantity: "+sum);
            
            System.out.println("Amount of Total Gift Wrapped: "+"$"+tol_wrap);
            System.out.println("Total Quantity Bill: "+"$"+tot_bill);
            System.out.println("*****************************************************************************");
        }*/

    }

    public void discount()throws IOException
    {
        cart_total=tot_bill;
        dis_name="NILL";
        if(tot_bill>200)
        {
            dis_name="flat_10_discount";
            dis=tot_bill*0.1;
            cart_total=tot_bill-dis;
        }
        if(sum>20)
        {
            dis_name="bulk_10_discount";
            dis=tot_bill*0.1;
            cart_total=tot_bill-dis;
        }
        if(apply_spl_dis==1)
        {
            dis_name="bulk_5_discount";
            dis=tot_bill*0.05;
            cart_total=tot_bill-dis;
        }
        if(apply_spl_dis==2)
        {
            int i;
             for(i=0;i<p;i++)
             {
            if(Integer.parseInt(cart_bill[i][2])>15 && sum>30)
            {
            Double new_price,tmp_str;
            int sss;
            dis_name="tiered_50_discount";
            sss=sum-15;
            new_price=Double.parseDouble(price_str.substring(3))*sss;
            dis=new_price*0.5;
            cart_total=tot_bill-dis;
            }
             }
        }

       int pack=0,i,check=0;
        for(i=1;i<=sum;i++)
        {
            if(sum==i*10)
            {
                check=1;
            pack=Math.round((sum/10));
            }
            if(check==0)
            pack=Math.round(((sum/10)+1));
        }
        tot_pack=(pack*5);




    }

    public void display()throws IOException
    {
        discount();
        int i,j;
        Double sub_total;
        sub_total=cart_total+tol_wrap+tot_pack;
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("\n\t\t\t\t\t\t\t\t\tTotal Bill Deatils");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.print(ds[0][0]+" ");
        System.out.print(ds[0][1]+" ");
        System.out.print("\t\tProduct_Quantity"+" ");
        System.out.print("\t\tIs_Product_is_Wrapped"+" ");
        System.out.print("\t\tTotal_Quantity_Price"+" ");
        System.out.print("\t\tWarpped_Amount"+" "+"\n");

    
        for(i=0;i<p;i++)
        {
                System.out.print(cart_bill[i][0]+" ");
                System.out.print("\t\t"+cart_bill[i][1]+" ");
                System.out.print("\t\t\t"+cart_bill[i][2]+" ");
                System.out.print("\t\t\t\t"+cart_bill[i][3]+" ");
                System.out.print("\t\t\t\t"+"$"+cart_bill[i][4]+" ");
                System.out.print("\t\t\t\t"+"$"+cart_bill[i][5]+" ");
                System.out.println("\n");
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("\n\t\t\t\t\t\t\t\t\tTotal Quantity: "+sum);
        System.out.println("\n\t\t\t\t\t\t\t\t\tTotal Quantity Bill: "+"$"+tot_bill);
        System.out.println("\n\t\t\t\t\t\t\t\t\tDiscount Name: "+dis_name);
        System.out.println("\n\t\t\t\t\t\t\t\t\tDiscounted Amount: "+dis);
        System.out.println("\n\t\t\t\t\t\t\t\t\tCart Bill: "+"$"+cart_total);
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("\n\t\t\t\t\t\t\t\t\tGift Wrap Fee: "+"$"+tol_wrap);
        System.out.println("\n\t\t\t\t\t\t\t\t\tShipping Fee: "+"$"+tot_pack);
        System.out.println("____________________________________________________________________________________________________________________________________________________");
        System.out.println("\n\t\t\t\t\t\t\t\t\tSub Total: "+"$"+sub_total);
        System.out.println("____________________________________________________________________________________________________________________________________________________");

       /* if(sum>10)
        {
            pack=Math.round((sum/10))
        }*/
        
            
        
    }


    public static void main(String args[])throws IOException
    {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        online_shop obj=new online_shop();
        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.println("\n\t\t\tWelcome To Online Shopping");
        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.println("\n\t\t\tSelect Your Choice");
        System.out.println("\n\t\t\t1.Enter Shopping Cart");
        System.out.println("\n\t\t\t2.Proceed To Cart");
        System.out.println("\n\t\t\t3.Exit");
        while(true)
        {
        System.out.print("\nUser Choice: ");
        int ch=Integer.parseInt(br.readLine());
        switch(ch)
        {
            case 1:
            {
                obj.data_set();
                obj.cart_billing();
                break;
            }

            case 2:
            {
                obj.display();
                break;
            }

            case 3:
            {
                System.exit(0);
            }
            default:
            {
                System.out.println("\n_________________Invalid Choice___________");
            }
        }
        
        }//while

    

    }//public
}//class