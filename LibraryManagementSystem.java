import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

class Book
{
    String name,author_name;
    String[] member_names;
    int no_copies,year_published,book_number,left_copies;
    Book next;

    Book(String name,String author_name,int book_number,int year_published,int no_copies)
    {
        this.name = name;
        this.author_name = author_name;
        this.no_copies = no_copies;
        this.left_copies = no_copies;
        this.year_published = year_published;
        this.book_number = book_number;
        member_names = new String[this.no_copies];

        for(int i=0;i<no_copies;i++)
        {
            member_names[i] = "";
        }

        next = null;
    }

}

class IssuedBook
{
    String book_name,author_name,issue_date,return_date;
    int book_number;
    IssuedBook next;

    IssuedBook(String book_name,String author_name,int book_number,String issue_date,String return_date)
    {
        this.book_name = book_name;
        this.author_name = author_name;
        this.book_number = book_number;
        this.issue_date = issue_date;
        this.return_date = return_date;
        this.next = null;
    }

}

class Member
{
    public final static int MAX_BOOK_ORDINARY = 7;
    public final static int MAX_BOOK_PREMIUM = 10;
    public final static int FINE_ORDINARY = -10;
    public final static int FINE_PREMIUM = -5;
    public final static int MAX_DAYS_ORDINARY = 10;
    public final static int MAX_DAYS_PREMIUM = 15;

    String name;
    int unique_id ,fine=0;
    boolean premium = false;
    Member next;

    int max_books,max_days,nodes=0;
    IssuedBook issuedBooks;

    Member(String name, int unique_id, boolean premium)
    {
        this.name = name;
        this.unique_id = unique_id;
        this.premium = premium;
        this.fine=0;
        if(this.premium)
        {
            this.max_books = MAX_BOOK_PREMIUM;
            this.max_days = MAX_DAYS_PREMIUM;
        }
        else
        {
            this.max_books = MAX_BOOK_ORDINARY;
            this.max_days = MAX_DAYS_ORDINARY;
        }
        this.issuedBooks = null;
    }
    Member(String name, int unique_id, boolean premium, int money)
    {
        this.name = name;
        this.unique_id = unique_id;
        this.premium = premium;

        if(this.premium)
        {
            this.max_books = MAX_BOOK_PREMIUM;
            this.max_days = MAX_DAYS_PREMIUM;
            this.fine = money-350;
        }
        else
        {
            this.max_books = MAX_BOOK_ORDINARY;
            this.max_days = MAX_DAYS_ORDINARY;
            this.fine = money-100;
        }

        this.issuedBooks = null;
    }

}

public class LibraryManagementSystem
{
    public static Member member_start=null;
    public static Book book_start=null;

    public static Book addBook(Book start,String name,String author_name,int book_number,int year_published,int no_copies)
    {
        Book temp = new Book(name,author_name,book_number,year_published,no_copies);
        if(start==null) return temp;
        Book current = start;
        while(current.next!=null)
            current = current.next;

        current.next = temp;
        return book_start;
    }

    public static void printBook(Book start)
    {
        Book current = start;
        while(current!=null)
        {
            System.out.println("\nBook Title                         : " + current.name);
            System.out.println("Author                             : " + current.author_name);
            System.out.println("Book Number                        : " + current.book_number);
            System.out.println("Year Of Publication                : " + current.year_published);
            System.out.println("Total Copies of the Book           : " + current.no_copies);
            System.out.println("Total Available Copies of the Book : " + current.left_copies);
            current = current.next;
        }
    }

    public static Book getBook(Book start,int book_number)
    {
        Book current = start;
        while(current!=null)
        {
            if(current.book_number == book_number) return current;
            current = current.next;
        }
        return null;
    }

    public static boolean findBookByName(Book start,String name)
    {
        Book current = start;
        while(current!=null)
        {
            if(current.name.contains(name)) return true;
            current = current.next;
        }
        return false;
    }

    public static boolean findBookByAuthor(Book start,String author_name)
    {
        Book current = start;
        while(current!=null)
        {
            if(current.author_name.contains(author_name)) return true;
            current = current.next;
        }
        return false;
    }

    public static boolean findBookByNumber(Book start,int book_number)
    {
        Book current = start;
        while(current!=null)
        {
            if(current.book_number == book_number) return true;
            current = current.next;
        }
        return false;
    }

    public static boolean findBookByName_Author(Book start,String name,String author_name)
    {
        Book current = start;
        while(current!=null)
        {
            if(current.name.contains(name) && current.author_name.contains(author_name)) return true;
            current = current.next;
        }
        return false;
    }

    public static void printFoundedBookName(Book start,String name)
    {
        Book current = start;
        System.out.println("\nHere are the books having Name '"+name+"'");
        System.out.println();
        while(current!=null)
        {
            if(current.name.contains(name))
            {
                System.out.println("Book Title             : "+current.name);
                System.out.println("Author                 : "+current.author_name);
                System.out.println("Year                   : "+current.year_published);
                System.out.println("No of available copies : "+current.no_copies);
                System.out.println();
                return;
            }
            current = current.next;
        }
    }

    public static void printFoundedBookAuthor(Book start,String author_name)
    {
        Book current = start;
        System.out.println("\nHere are the books having Author Name '"+author_name+"'");
        System.out.println();
        while(current!=null)
        {
            if(current.author_name.contains(author_name))
            {
                System.out.println("Book Title             : "+current.name);
                System.out.println("Author                 : "+current.author_name);
                System.out.println("Year                   : "+current.year_published);
                System.out.println("No of available copies : "+current.no_copies);
                System.out.println();
            }
            current = current.next;
        }
    }

    public static void printFoundedBookNumber(Book start,int book_number)
    {
        Book current = start;
        System.out.println("\nHere is the Book having Book Number '"+book_number+"'");
        System.out.println();
        while(current!=null)
        {
            if(current.book_number == book_number)
            {
                System.out.println("Book Title             : "+current.name);
                System.out.println("Author                 : "+current.author_name);
                System.out.println("Year                   : "+current.year_published);
                System.out.println("No of available copies : "+current.no_copies);
                System.out.println();
                return;
            }
            current = current.next;
        }
    }

    public static void printFoundedBookName_Author(Book start,String name,String author_name)
    {
        Book current = start;
        System.out.println("\nHere are the books having Name '"+name+"' and Author Name '"+author_name+"'");
        System.out.println();
        while(current!=null)
        {
            if(current.name.contains(name) && current.author_name.contains(author_name))
            {
                System.out.println("Book Title             : "+current.name);
                System.out.println("Author                 : "+current.author_name);
                System.out.println("Year                   : "+current.year_published);
                System.out.println("No of available copies : "+current.no_copies);
                System.out.println();
                return;
            }
            current = current.next;
        }
    }

    public static String getBookName(Book start,int book_number)
    {
        Book current = start;
        while(current!=null)
        {
            if(current.book_number == book_number) return current.name;
            current = current.next;
        }
        return "";
    }

    public static boolean checkLeftCopies(Book start,int book_number)
    {
        Book current = start;
        while(current!=null)
        {
            if(current.book_number == book_number)
            {
                if(current.left_copies>0) return false;
                else return true;
            }
            current = current.next;
        }
        return false;
    }

    public static void setNameToBook(Book start,String name,int book_number)
    {
        Book current = start;
        while(current!=null)
        {
            if(current.book_number == book_number)
            {
                for(int i=0;i<current.member_names.length;i++)
                {
                    if(current.member_names[i].equals(""))
                    {
                        current.member_names[i] = name;
                        current.left_copies--;
                        return;
                    }
                }
            }
            current = current.next;
        }
    }

    public static void freeNameToBook(Book start,String name,int book_number)
    {
        Book current = start;
        while(current!=null)
        {
            if(current.book_number == book_number)
            {
                for(int i=0;i<current.member_names.length;i++)
                {
                    if(current.member_names[i].equals(name))
                    {
                        current.member_names[i] = "";
                        current.left_copies++;
                        return;
                    }
                }
            }
            current = current.next;
        }
    }

    public static void printBookIssuedToByBookNumber(Book start,int book_number)
    {
        Book current = start;
        System.out.println("\nNames of the Member having the Book Title '"+getBookName(start,book_number)+"' & Book Number '"+book_number+"'");
        System.out.println();
        while(current!=null)
        {
            if(current.book_number == book_number)
            {
                for(int i=0;i<current.member_names.length;i++)
                {
                    if(current.member_names[i]!="")
                    {
                        System.out.println("Copy "+(i+1)+" is issued to '"+current.member_names[i]+"'");
                    }
                }
                return;
            }
            current = current.next;
        }
    }

    public static Member addMemberDefault(Member start,String name,int unique_id,boolean premium)
    {
        Member temp = new Member(name, unique_id, premium);
        if(start==null) return temp;
        Member current = start;
        while(current.next!=null)
            current = current.next;
        current.next = temp;
        return start;
    }

    public static Member addMember(Member start,String name,int unique_id,boolean premium,int money)
    {
        Member temp = new Member(name, unique_id, premium,money);
        if(start==null) return temp;
        Member current = start;

        while(current.next!=null)
        {
            current = current.next;
        }
        current.next = temp;
        return start;
    }

    public static void printMember(Member start)
    {
        Member current = start;
        while(current!=null)
        {
            System.out.println("\nName of The Member              : " + current.name);
            System.out.println("Unique ID of The Member         : " + current.unique_id);
            System.out.print("Is Premium Member               : ");
            if(current.premium)
            {
                System.out.print("YES");
            }
            else 
            {
                System.out.print("NO");
            }
            System.out.println("\nCurrent Number of Book Issued   : " + current.nodes);

            current = current.next;
        }
    }

    public static boolean findMemberByName(Member start,String name)
    {
        Member current = start;
        while(current!=null)
        {
            if(current.name.equals(name)) return true;
            current = current.next;
        }
        return false;
    }

    public static void printFoundedMemberByName(Member start,String name)
    {
        Member current = start;
        System.out.println("\nMember having Name '"+name+"' Found here are his/her details : ");
        System.out.println();
        while(current!=null)
        {
            if(current.name.equals(name))
            {
                System.out.println("Name of the Member      : "+ current.name);
                System.out.println("Unique ID of the Member : "+ current.unique_id);
                if(current.premium) System.out.println("This Member has Premium Membership");
                if(current.nodes!= 0) 
                {
                    System.out.println("All the Book Issued by Member are listed below : ");
                    IssuedBook curr = current.issuedBooks;
                    while(curr!=null)
                    {
                        System.out.println("Book Title  : " + curr.book_name);
                        System.out.println("Author Name : " + curr.author_name);
                        System.out.println("Book Number : " + curr.book_number);
                        System.out.println("Issue Date  : " + curr.issue_date);
                        curr = curr.next;
                    }
                    System.out.println();
                }
                return;
            }
            current = current.next;
        }
    }

    public static boolean findMemberByID(Member start,int ID)
    {
        Member current = start;
        while(current!=null)
        {
            if(current.unique_id == ID) return true;
            current = current.next;
        }
        return false;
    }

    public static void printFoundedMemberByID(Member start,int ID)
    {
        Member current = start;
        System.out.println("\nMember having ID '"+ID+"' Found here are his/her details : ");
        System.out.println();
        while(current!=null)
        {
            if(current.unique_id == ID)
            {
                System.out.println("Name of the Member      : "+ current.name);
                System.out.println("Unique ID of the Member : "+ current.unique_id);
                if(current.premium) System.out.println("This Member has Premium Membership");
                if(current.nodes!= 0) 
                {
                    System.out.println("All the Book Issued by Member are listed below : ");
                    IssuedBook curr = current.issuedBooks;
                    while(curr!=null)
                    {
                        System.out.println("Book Title  : " + curr.book_name);
                        System.out.println("Author Name : " + curr.author_name);
                        System.out.println("Book Number : " + curr.book_number);
                        System.out.println("Issue Date  : " + curr.issue_date);
                        curr = curr.next;
                    }
                    System.out.println();
                }
                return;
            }
            current = current.next;
        }
    }

    public static String getMemberName(Member start,int ID)
    {
        Member current = start;
        while(current!=null)
        {
            if(current.unique_id == ID) return current.name;
            current = current.next;
        }
        return "";
    }

    public static void checkFine(Member start,int ID)
    {
        Member current = start;
        System.out.print("\nMember having ID '"+ID+"' and Name '"+getMemberName(start, ID)+"' having Fine Amount in his/her Account : ");
        while(current!=null)
        {
            if(current.unique_id == ID)
            {
                System.out.println(current.fine);
                System.out.println("\nPlease Note - Here -ve value means amount left to pay");
                System.out.println("Please Note - Here +ve value means Extra Amount is Paid Already");
                return;
            }
            current = current.next;
        }
    }

    public static void payFine(Member start,int ID,int money)
    {
        Member current = start;
        while(current!=null)
        {
            if(current.unique_id == ID)
            {
                current.fine = current.fine + money;
                return;
            }
            current = current.next;
        }
    }

    public static String getTodayDate()
    {
        LocalDate l = LocalDate.now();
        DateTimeFormatter formated = DateTimeFormatter.ofPattern("EEEE : dd-MM-yyyy");
        String date = l.format(formated);
        return date;
    }

    public static String get10DaysFromNow()
    {
        LocalDate l = LocalDate.now().plusDays(10);
        DateTimeFormatter formated = DateTimeFormatter.ofPattern("EEEE : dd-MM-yyyy");
        String date = l.format(formated);
        return date;
    }

    public static String get15DaysFromNow()
    {
        LocalDate l = LocalDate.now().plusDays(15);
        DateTimeFormatter formated = DateTimeFormatter.ofPattern("EEEE : dd-MM-yyyy");
        String date = l.format(formated);
        return date;
    }

    public static int differenceBetweenDates(String date1,String date2)
    {
        DateTimeFormatter formated = DateTimeFormatter.ofPattern("EEEE : dd-MM-yyyy");
        LocalDate d1 = LocalDate.parse(date1,formated);
        LocalDate d2 = LocalDate.parse(date2,formated);
        long diff = ChronoUnit.DAYS.between(d1, d2);
        return (int)diff;
    }

    public static void buyPremium(Member start,int ID,int money)
    {
        Member current = start;
        while(current!=null)
        {
            if(current.unique_id == ID)
            {
                current.fine = current.fine + money - 250;
                current.premium = true;
                current.max_books = Member.MAX_BOOK_PREMIUM;
                current.max_days = Member.MAX_DAYS_PREMIUM;
                return;
            }
            current = current.next;
        }
    }

    public static boolean findBook(Book start,String name,String Author,int book_number)
    {
        Book current = start;
        while(current!=null)
        {
            if(current.name.equals(name) && current.author_name.equals(Author) && current.book_number==book_number) return true;
            current = current.next;
        }

        return false;
    }

    public static IssuedBook setIssueBook(IssuedBook start,String name,String author,int book_number,String issue_date,String return_date)
    {
        IssuedBook temp = new IssuedBook(name, author, book_number, issue_date, return_date);
        if(start==null) return temp;
        IssuedBook current = start;
        while(current.next!=null)
        {
            current = current.next;
        }
        current.next = temp;
        return start;
    }

    public static boolean findIssuedBook(IssuedBook start,int book_number)
    {
        IssuedBook current = start;
        while(current!=null)
        {
            if(current.book_number == book_number) return true;
            current = current.next;
        }
        return false;
    }

    public static String getReturnDate(IssuedBook start,int book_number)
    {
        IssuedBook current = start;
        while(current!=null)
        {
            if(current.book_number == book_number) return current.return_date;
            current = current.next;
        }
        return "";
    }

    public static IssuedBook freeIssuedBook(IssuedBook start,int book_number)
    {
        if(start.next==null && start.book_number == book_number)
        {
            return null;
        }

        if(start.book_number == book_number)
        {
            return start.next;
        } 
        IssuedBook current = start.next;
        IssuedBook previous = start;
        while(current!=null && current.book_number!=book_number)
        {
            previous = current;
            current = current.next;
        }
        previous.next = current.next;
        return start;
    }

    public static void printIssuedBookByMember(IssuedBook start)
    {
        IssuedBook current = start;
        while(current!=null)
        {
            System.out.println("\nIssued Book Title  : " + current.book_name);
            System.out.println("Issued Book Author : " + current.author_name);
            System.out.println("Issued Book Number : " + current.book_number);
            System.out.println("Issue Date         : " + current.issue_date);
            System.out.println("Return Date        : " + current.return_date);
            current = current.next;
        }
    }

    public static Member getMember(Member start,int ID)
    {
        Member current = start;
        while(current!=null)
        {
            if(current.unique_id == ID) return current;
            current = current.next;
        }
        return null;
    }

    public static boolean checkBook(IssuedBook start,int book_number)
    {
        IssuedBook current = start;
        while(current!=null)
        {
            if(current.book_number == book_number) return true;
            current = current.next;
        }
        return false;
    }

    public static void issueBook(Member m_start,Book b_start,int ID,String book_name,String author,int book_number)
    {
        if(findMemberByID(m_start, ID))
        {
            if(findBook(b_start,book_name,author,book_number))
            {
                Member current = m_start;
                while(current!=null)
                {
                    if(current.unique_id == ID)
                    {
                        if(current.nodes < current.max_books)
                        {
                            if(checkBook(current.issuedBooks, book_number))
                            {
                                System.out.println("\nBook Already Issued cannot Issue More Copy of the Same Book");
                                return;
                            }
                            if(checkLeftCopies(b_start, book_number))
                            {
                                System.out.println("\nNo More Copies Left at Present");
                                return;
                            }
                            setNameToBook(b_start,current.name,book_number);
                            String curr_date = getTodayDate();
                            String return_date;
                            if(current.premium) return_date = get15DaysFromNow();
                            else return_date = get10DaysFromNow();
                            current.issuedBooks = setIssueBook(current.issuedBooks, book_name, author, book_number,curr_date,return_date);
                            current.nodes++;

                            System.out.println("\nBook Title '"+book_name+"' Author '"+author+"' and Book No. '"+book_number+"'");
                            System.out.println("Is successfully Issued to Member Name '"+current.name+"' & ID '"+current.unique_id+"'");

                            System.out.println("Happy Reading");

                            return;
                        }
                        else
                        {
                            System.out.println("\nMax No. of Book Issued to the Member");
                            System.out.println("Unable to Issue Book");
                            return;
                        }
                    }
                    current = current.next;
                }
            }
            else
            {
                System.out.println("\nNo such Book with Title '"+book_name+"' Author '"+author+"' & Book No. '"+book_number+"' is present");
            }
        }
        else
        {
            System.out.println("\nNo such Member with ID '"+ID+"' Present");
        }

    }

    public static void returnBook(Member m_start,Book b_start,int ID,String book_name,String author,int book_number)
    {
        if(findMemberByID(m_start, ID))
        {
            if(findBook(b_start,book_name,author,book_number))
            {
                Member current = m_start;
                while(current!=null)
                {
                    if(current.unique_id == ID)
                    {
                        if(findIssuedBook(current.issuedBooks, book_number))
                        {
                            current.nodes--;
                            String current_day = getTodayDate();
                            int diff = differenceBetweenDates(getReturnDate(current.issuedBooks, book_number), current_day);
                            if(diff!=0)
                            {
                                if(current.premium)
                                {
                                    if(diff>Member.MAX_DAYS_PREMIUM) current.fine = current.fine + diff*Member.FINE_PREMIUM;
                                }
                                else 
                                {
                                    if(diff>Member.MAX_DAYS_ORDINARY) current.fine = current.fine + diff*Member.FINE_ORDINARY;
                                }
                            }
                            current.issuedBooks = freeIssuedBook(current.issuedBooks, book_number);
                            freeNameToBook(b_start, current.name, book_number);
                            System.out.println("\nBook Title '"+book_name+"' Author '"+author+"' & Book No."+book_number+"' is Successfully Returned");
                            return;
                        }
                        else
                        {
                            System.out.println("\nNo Book with Title '"+book_name+"' Author '"+author+"' & Book No. '"+book_number+"' is Issued To the Member");
                            return;
                        }
                    }
                    current = current.next;
                }
            }
            else
            {
                System.out.println("\nNo such Book with Title '"+book_name+"' Author '"+author+"' & Book No. '"+book_number+"' is present");
            }
        }
        else
        {
            System.out.println("\nNo such Member with ID '"+ID+"' Present");
        }
    }

    
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        Simulation.utilityFunction1();
        Simulation.utilityFunction2();

        System.out.println("******************************* Welcome to Library Management System *******************************");
        int choice=0;
        do
        {
            System.out.println();
            System.out.println("Press 1  - To Issue A Book");
            System.out.println("Press 2  - To Return A Book");
            System.out.println("Press 3  - To Add Book");
            System.out.println("Press 4  - To Print All The Books");
            System.out.println("Press 5  - To Find Book");
            System.out.println("Press 6  - To Print Names of Member Having Particular Book");
            System.out.println("Press 7  - To Add Member");
            System.out.println("Press 8  - To Print Details Of The Members");
            System.out.println("Press 9  - To Find Member");
            System.out.println("Press 10 - To Check Fine");
            System.out.println("Press 11 - To Pay Fine");
            System.out.println("Press 12 - To Buy Premium Membership");
            System.out.println("Press 13 - To Print All Book Issued By A Member");
            System.out.println("Press 0  - To Exit");

            System.out.print("\nYour Choice : ");
            choice = in.nextInt();
            
            switch(choice)
            {
                case 1:
                {
                    int ID,book_num;
                    String book_name,author;

                    System.out.print("\nPlease Enter Member ID          : ");
                    ID = in.nextInt();
                    System.out.print("Please Enter The Book No.       : ");
                    book_num = in.nextInt();
                    System.out.print("Please Enter The Title of Book  : ");
                    in.nextLine();
                    book_name = in.nextLine();
                    System.out.print("Please Enter The Name of Author : ");
                    author = in.nextLine();

                    issueBook(member_start, book_start, ID, book_name, author, book_num);
                    System.out.println();
                    break;
                }
                case 2:
                {
                    int ID,book_num;
                    String book_name,author;

                    System.out.print("\nPlease Enter Member ID          : ");
                    ID = in.nextInt();
                    System.out.print("Please Enter The Book No.       : ");
                    book_num = in.nextInt();
                    System.out.print("Please Enter The Title of Book  : ");
                    in.nextLine();
                    book_name = in.nextLine();
                    System.out.print("Please Enter The Name of Author : ");
                    author = in.nextLine();
                    returnBook(member_start, book_start, ID, book_name, author, book_num);
                    System.out.println();
                    break;
                }
                case 3:
                {
                    String book_name,author;
                    int book_number,year_published,no_copies;

                    System.out.print("\nPlease Enter Book No             : ");
                    book_number = in.nextInt();
                    System.out.print("Please Enter Book Title          : ");
                    in.nextLine();
                    book_name = in.nextLine();
                    System.out.print("Please Enter The Name of Author  : ");
                    author = in.nextLine();
                    System.out.print("Please Enter Year of Publication : ");
                    year_published = in.nextInt();
                    System.out.print("Please Enter No. of Copies       : ");
                    no_copies = in.nextInt();

                    book_start = addBook(book_start, book_name, author, book_number, year_published, no_copies);
                    System.out.println("\nBook Successfully Added to the Library");
                    break;
                }
                case 4:
                {
                    System.out.println("\nHere are the Details of all the Books in Library");
                    printBook(book_start);
                    break;
                }
                case 5:
                {
                    System.out.println("\nPress 1 - To Search Book By Name");
                    System.out.println("Press 2 - To Search Book By Author Name");
                    System.out.println("Press 3 - To Search Book By Book Number");
                    System.out.println("Press 4 - To Search Book By Name & Author Name");
                    System.out.print("\nYour Choice : ");
                    int choice1 = in.nextInt();

                    switch(choice1)
                    {
                        case 1:
                        {
                            in.nextLine();
                            System.out.print("Please Enter Name : ");
                            String name = in.nextLine();
                            if(findBookByName(book_start, name))
                            {
                                printFoundedBookName(book_start, name);
                                break;
                            }
                            else
                            {
                                System.out.println("\nNo Book Found Containing Book Name '"+name+"'");
                                break;
                            }
                        }
                        case 2:
                        {
                            in.nextLine();
                            System.out.print("Please Enter the Name of Author : ");
                            String author = in.nextLine();

                            if(findBookByAuthor(book_start, author))
                            {
                                printFoundedBookAuthor(book_start, author);
                                break;
                            }
                            else
                            {
                                System.out.println("\nNo Book Found Containing Author Name '"+author+"'");
                                break;
                            }
                        }
                        case 3:
                        {
                            System.out.print("Please Enter The Book Number : ");
                            int book_number = in.nextInt();
                            if(findBookByNumber(book_start, book_number))
                            {
                                printFoundedBookNumber(book_start, book_number);
                                break;
                            }
                            else
                            {
                                System.out.println("\nNo Book Found Having Book Number '"+book_number+"'");
                                break;
                            }
                        }
                        case 4:
                        {
                            in.nextLine();
                            String name,author;
                            System.out.print("Please Enter Name of Book : ");
                            name = in.nextLine();
                            System.out.print("Please Enter Author Name  : ");
                            author = in.nextLine();

                            if(findBookByName_Author(book_start, name, author))
                            {
                                printFoundedBookName_Author(book_start,name,author);
                                break;
                            }
                            else
                            {
                                System.out.println("\nNo Book Having Title '"+name+"' & Author Name '"+author+"' Found");
                                break;
                            }
                        }
                        default:
                        {
                            System.out.println("\nWrong Choice");
                            break;
                        }
                    }
                    break;
                }
                case 6:
                {
                    System.out.print("\nPlease Enter The Book Number : ");
                    int book_number = in.nextInt();
                    if(findBookByNumber(book_start, book_number))
                    {
                        Book temp = getBook(book_start, book_number);
                        if(temp.left_copies != temp.no_copies)
                        {
                            printBookIssuedToByBookNumber(book_start, book_number);
                        }
                        else
                        {
                            System.out.println("\nNo Copies of this Book have been Issued");
                        }
                        break;
                    }
                    else
                    {
                        System.out.println("\nNo Book Found Having Book Number '"+book_number+"'");
                        break;
                    }
                }
                case 7:
                {
                    String name;
                    int ID,money,premium;

                    System.out.print("\nPlease Enter The Name of The Member                            : ");
                    in.nextLine();
                    name = in.nextLine();
                    System.out.print("Please Enter The Unique ID of The Member (B/w 1000000-9999999) : ");
                    ID = in.nextInt();
                    System.out.println("You Want to Buy Premium Membership");
                    System.out.println("Press 1 for Yes");
                    System.out.println("Press 2 for No");
                    System.out.print("\nYour Choice : ");
                    premium = in.nextInt();
                    if(premium==1)
                    {
                        System.out.println("'Premium Membership Costs Rupees 350' OR 'You can Pay Partially");
                        System.out.print("Please Enter The Amount To Pay : ");
                        money = in.nextInt();
                        member_start = addMember(member_start, name, ID, true, money);
                        System.out.println("\nMember Added Successfully");
                        break;
                    }
                    else if(premium==2)
                    {
                        System.out.println("'Ordinary Membership Costs Rupees 100' OR 'You can Pay Partially");
                        System.out.print("Please Enter The Amount To Pay : ");
                        money = in.nextInt();
                        member_start = addMember(member_start, name, ID, false, money);
                        System.out.println("\nMember Added Successfully");
                        break;
                    }
                    else
                    {
                        System.out.println("\nWrong Choice");
                        break;
                    }
                }
                case 8:
                {
                    System.out.println("\nHere are the Details of all the Member of Library");
                    printMember(member_start);
                    break;
                }
                case 9:
                {
                    System.out.println("\nPress 1 - To Find Member By Name");
                    System.out.println("Press 2 - To Find Member By ID");
                    System.out.print("\nYour Choice : ");
                    int choice2 = in.nextInt();

                    switch(choice2)
                    {
                        case 1:
                        {
                            String name;
                            System.out.print("Please Enter The Name of The Member : ");
                            in.nextLine();
                            name = in.nextLine();

                            if(findMemberByName(member_start, name))
                            {
                                printFoundedMemberByName(member_start, name);
                                break;
                            }
                            else
                            {
                                System.out.println("\nNo Member Having Name '"+name+"' is there");
                                break;
                            }
                        }
                        case 2:
                        {
                            int ID;
                            System.out.println("Please Enter The Unique ID of The Member : ");
                            ID = in.nextInt();

                            if(findMemberByID(member_start, ID))
                            {
                                printFoundedMemberByID(member_start, ID);
                                break;
                            }
                            else
                            {
                                System.out.println("\nNo Member Having ID '"+ID+"' is there");
                                break;
                            }
                        }
                        default:
                        {
                            System.out.println("\nWrong Choice");
                            break;
                        }
                    }
                    break;
                }
                case 10:
                {
                    int ID;
                    System.out.print("Please Enter The Unique ID of The Member : ");
                    ID = in.nextInt();

                    if(findMemberByID(member_start, ID))
                    {
                        checkFine(member_start, ID);
                        break;
                    }
                    else
                    {
                        System.out.println("\nNo Member Having ID '"+ID+"' is there");
                        break;
                    }
                }
                case 11:
                {
                    int ID,money;
                    System.out.print("Please Enter The Unique ID of The Member : ");
                    ID = in.nextInt();
                    if(findMemberByID(member_start, ID))
                    {
                        System.out.print("Please Enter The Amount To Pay : ");
                        money = in.nextInt();
                        payFine(member_start, ID, money);
                        break;
                    }
                    else
                    {
                        System.out.println("\nNo Member Having ID '"+ID+"' is there");
                        break;
                    }
                }
                case 12:
                {
                    int ID,money;
                    System.out.print("Please Enter The Unique ID of The Member : ");
                    ID = in.nextInt();
                    if(findMemberByID(member_start, ID))
                    {
                        System.out.println("Premium Membership costs 250 for Existing Member");
                        System.out.println("'Pay 250' OR 'Pay Partially'");
                        System.out.print("Please Enter the Amount : ");
                        money = in.nextInt();
                        buyPremium(member_start, ID, money);
                        break;
                    }
                    else
                    {
                        System.out.println("\nNo Member Having ID '"+ID+"' is there");
                        break;
                    }
                }
                case 13:
                {
                    int ID;
                    System.out.print("Please Enter The Unique ID of the Member : ");
                    ID = in.nextInt();
                    if(findMemberByID(member_start, ID))
                    {
                        Member temp = getMember(member_start, ID);
                        if(temp.nodes!=0)
                        {
                            System.out.println("Here are the Details of the Book Issued By the Member '"+getMemberName(member_start, ID)+"' ID '"+ID+"'");
                            printIssuedBookByMember(temp.issuedBooks);
                        }
                        else
                        {
                            System.out.println("\nMember with ID '"+ID+"' haven't Issued any Books yet");
                            System.out.println("Please Issue Some Books First :)");
                        }
                        break;
                    }
                    else
                    {
                        System.out.println("\nNo Member Having ID '"+ID+"' is there");
                        break;
                    }
                }
                default:
                {
                    System.out.println("You are Exiting Library Management System");
                    break;
                }
            }
        }
        while(choice!=0);

        System.out.println("******************************* Thanks For Using Library Management System *******************************");

        in.close();
    }
}

class Simulation
{
    public static void utilityFunction1()
    {
        LibraryManagementSystem.book_start = LibraryManagementSystem.addBook(LibraryManagementSystem.book_start, "The Pragmatic Programmer", "Andrew Hunt, David Thomas", 124703, 1999, 5);
        LibraryManagementSystem.book_start = LibraryManagementSystem.addBook(LibraryManagementSystem.book_start, "Clean Code: A Handbook of Agile Software Craftsmanship", "Robert C. Martin", 539982, 2008, 3);
        LibraryManagementSystem.book_start = LibraryManagementSystem.addBook(LibraryManagementSystem.book_start, "Introduction to the Theory of Computation", "Michael Sipser", 170247, 2005, 8);
        LibraryManagementSystem.book_start = LibraryManagementSystem.addBook(LibraryManagementSystem.book_start, "Computer Networks: A Systems Approach", "Larry L. Peterson, Bruce S. Davie", 712456, 2011, 7);
        LibraryManagementSystem.book_start = LibraryManagementSystem.addBook(LibraryManagementSystem.book_start, "Artificial Intelligence: A Modern Approach", "Stuart Russell, Peter Norvig", 382189, 1995, 2);
        LibraryManagementSystem.book_start = LibraryManagementSystem.addBook(LibraryManagementSystem.book_start, "Algorithms, Part I", "Robert Sedgewick, Kevin Wayne", 978312, 2011, 6);
        LibraryManagementSystem.book_start = LibraryManagementSystem.addBook(LibraryManagementSystem.book_start, "The C Programming Language", "Brian W. Kernighan, Dennis M. Ritchie", 314971, 1978, 9);
        LibraryManagementSystem.book_start = LibraryManagementSystem.addBook(LibraryManagementSystem.book_start, "Introduction to Algorithms", "Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, Clifford Stein", 745321, 1990, 7);
        LibraryManagementSystem.book_start = LibraryManagementSystem.addBook(LibraryManagementSystem.book_start, "Database System Concepts", "Abraham Silberschatz, Henry F. Korth, S. Sudarshan", 139586, 1985, 4);
        LibraryManagementSystem.book_start = LibraryManagementSystem.addBook(LibraryManagementSystem.book_start, "Operating System Concepts", "Abraham Silberschatz, Peter B. Galvin, Greg Gagne", 928463, 1990, 8);
        LibraryManagementSystem.book_start = LibraryManagementSystem.addBook(LibraryManagementSystem.book_start, "Computer Architecture: A Quantitative Approach", "John L. Hennessy, David A. Patterson", 601374, 1990, 5);
        LibraryManagementSystem.book_start = LibraryManagementSystem.addBook(LibraryManagementSystem.book_start, "Pattern Recognition and Machine Learning", "Christopher M. Bishop", 281653, 2006, 3);
        LibraryManagementSystem.book_start = LibraryManagementSystem.addBook(LibraryManagementSystem.book_start, "Computer Graphics: Principles and Practice", "James D. Foley, Andries van Dam, Steven K. Feiner, John F. Hughes", 853712, 1995, 7);
        LibraryManagementSystem.book_start = LibraryManagementSystem.addBook(LibraryManagementSystem.book_start, "Introduction to Automata Theory, Languages, and Computation", "John E. Hopcroft, Rajeev Motwani, Jeffrey D. Ullman", 436251, 1979, 1);
        LibraryManagementSystem.book_start = LibraryManagementSystem.addBook(LibraryManagementSystem.book_start, "Design Patterns: Elements of Reusable Object-Oriented Software", "Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides", 713298, 1994, 9);
        LibraryManagementSystem.book_start = LibraryManagementSystem.addBook(LibraryManagementSystem.book_start, "Computer Organization and Design: The Hardware/Software Interface", "David A. Patterson, John L. Hennessy", 486210, 1994, 6);
        LibraryManagementSystem.book_start = LibraryManagementSystem.addBook(LibraryManagementSystem.book_start, "Introduction to Robotics: Mechanics and Control", "John J. Craig", 581406, 1989, 2);
        LibraryManagementSystem.book_start = LibraryManagementSystem.addBook(LibraryManagementSystem.book_start, "Artificial Intelligence: Foundations of Computational Agents", "David L. Poole, Alan K. Mackworth", 244609, 2010, 4);
        LibraryManagementSystem.book_start = LibraryManagementSystem.addBook(LibraryManagementSystem.book_start, "Computer Vision: Algorithms and Applications", "Richard Szeliski", 872394, 2010, 3);
        LibraryManagementSystem.book_start = LibraryManagementSystem.addBook(LibraryManagementSystem.book_start, "Introduction to Information Retrieval", "Christopher D. Manning, Prabhakar Raghavan, Hinrich Schütze", 507913, 2008, 9);
        LibraryManagementSystem.book_start = LibraryManagementSystem.addBook(LibraryManagementSystem.book_start, "Modern Operating Systems", "Andrew S. Tanenbaum, Herbert Bos", 190725, 2014, 5);
        LibraryManagementSystem.book_start = LibraryManagementSystem.addBook(LibraryManagementSystem.book_start, "Computer Networking: A Top-Down Approach", "James F. Kurose, Keith W. Ross", 874230, 2000, 8);
        LibraryManagementSystem.book_start = LibraryManagementSystem.addBook(LibraryManagementSystem.book_start, "Introduction to Data Mining", "Pang-Ning Tan, Michael Steinbach, Vipin Kumar", 398457, 2005, 7);
        LibraryManagementSystem.book_start = LibraryManagementSystem.addBook(LibraryManagementSystem.book_start, "The Mythical Man-Month: Essays on Software Engineering", "Frederick P. Brooks Jr.", 596783, 1975, 2);
        LibraryManagementSystem.book_start = LibraryManagementSystem.addBook(LibraryManagementSystem.book_start, "Computer Systems: A Programmer's Perspective", "Randal E. Bryant, David R. O'Hallaron", 264713, 2010, 4);
        LibraryManagementSystem.book_start = LibraryManagementSystem.addBook(LibraryManagementSystem.book_start, "Introduction to Compiler Design", "Torben Ægidius Mogensen", 852301, 1992, 1);
        LibraryManagementSystem.book_start = LibraryManagementSystem.addBook(LibraryManagementSystem.book_start, "Computer Architecture: Concepts and Evolution", "Gerrit A. Blaauw, Frederick P. Brooks Jr.", 314562, 1997, 6);
        LibraryManagementSystem.book_start = LibraryManagementSystem.addBook(LibraryManagementSystem.book_start, "Computer Organization and Architecture: Designing for Performance", "William Stallings", 709168, 2015, 8);
        LibraryManagementSystem.book_start = LibraryManagementSystem.addBook(LibraryManagementSystem.book_start, "Software Engineering: A Practitioner's Approach", "Roger S. Pressman, Bruce Maxim", 539615, 2015, 3);
        LibraryManagementSystem.book_start = LibraryManagementSystem.addBook(LibraryManagementSystem.book_start, "Introduction to Formal Languages and Automata", "Peter Linz", 614092, 2000, 7);
        LibraryManagementSystem.book_start = LibraryManagementSystem.addBook(LibraryManagementSystem.book_start, "Information Theory, Inference, and Learning Algorithms", "David MacKay", 326958, 2003, 5);
        LibraryManagementSystem.book_start = LibraryManagementSystem.addBook(LibraryManagementSystem.book_start, "Introduction to Parallel Computing", "Ananth Grama, Anshul Gupta, George Karypis, Vipin Kumar", 821457, 2003, 2);
        LibraryManagementSystem.book_start = LibraryManagementSystem.addBook(LibraryManagementSystem.book_start, "Computer Systems: A Programmer's Perspective", "Bryant O'Hallaron", 291847, 2016, 6);
        LibraryManagementSystem.book_start = LibraryManagementSystem.addBook(LibraryManagementSystem.book_start, "Introduction to Computer Security", "Michael T. Goodrich, Roberto Tamassia", 672109, 2011, 9);
        LibraryManagementSystem.book_start = LibraryManagementSystem.addBook(LibraryManagementSystem.book_start, "Introduction to the Design and Analysis of Algorithms", "Anany Levitin", 932468, 2012, 3);
    }

    public static void utilityFunction2()
    {
        LibraryManagementSystem.member_start = LibraryManagementSystem.addMemberDefault(LibraryManagementSystem.member_start,"Aarav Patel", 735821, true);
        LibraryManagementSystem.member_start = LibraryManagementSystem.addMemberDefault(LibraryManagementSystem.member_start,"Nisha Gupta", 492617, false);
        LibraryManagementSystem.member_start = LibraryManagementSystem.addMemberDefault(LibraryManagementSystem.member_start,"Arjun Sharma", 819456, true);
        LibraryManagementSystem.member_start = LibraryManagementSystem.addMemberDefault(LibraryManagementSystem.member_start,"Priya Singh", 257903, false);
        LibraryManagementSystem.member_start = LibraryManagementSystem.addMemberDefault(LibraryManagementSystem.member_start,"Rohan Verma", 604712, true);
        LibraryManagementSystem.member_start = LibraryManagementSystem.addMemberDefault(LibraryManagementSystem.member_start,"Aisha Reddy", 963410, false);
        LibraryManagementSystem.member_start = LibraryManagementSystem.addMemberDefault(LibraryManagementSystem.member_start,"Vikram Kumar", 182596, true);
        LibraryManagementSystem.member_start = LibraryManagementSystem.addMemberDefault(LibraryManagementSystem.member_start,"Deepika Mehta", 536790, true);
        LibraryManagementSystem.member_start = LibraryManagementSystem.addMemberDefault(LibraryManagementSystem.member_start,"Siddharth Joshi", 895243, false);
        LibraryManagementSystem.member_start = LibraryManagementSystem.addMemberDefault(LibraryManagementSystem.member_start,"Sanjana Desai", 374019, true);
        LibraryManagementSystem.member_start = LibraryManagementSystem.addMemberDefault(LibraryManagementSystem.member_start,"Rahul Khanna", 726830, false);
        LibraryManagementSystem.member_start = LibraryManagementSystem.addMemberDefault(LibraryManagementSystem.member_start,"Ishita Sharma", 401578, true);
        LibraryManagementSystem.member_start = LibraryManagementSystem.addMemberDefault(LibraryManagementSystem.member_start,"Vivan Malhotra", 168935, true);
        LibraryManagementSystem.member_start = LibraryManagementSystem.addMemberDefault(LibraryManagementSystem.member_start,"Riya Choudhary", 549621, false);
        LibraryManagementSystem.member_start = LibraryManagementSystem.addMemberDefault(LibraryManagementSystem.member_start,"Aryan Kapoor", 970512, true);
        LibraryManagementSystem.member_start = LibraryManagementSystem.addMemberDefault(LibraryManagementSystem.member_start,"Ishani Srinivasan", 285736, true);
        LibraryManagementSystem.member_start = LibraryManagementSystem.addMemberDefault(LibraryManagementSystem.member_start,"Advait Patel", 621804, false);
        LibraryManagementSystem.member_start = LibraryManagementSystem.addMemberDefault(LibraryManagementSystem.member_start,"Ananya Iyer", 476210, true);
        LibraryManagementSystem.member_start = LibraryManagementSystem.addMemberDefault(LibraryManagementSystem.member_start,"Dhruv Rao", 830149, false);
        LibraryManagementSystem.member_start = LibraryManagementSystem.addMemberDefault(LibraryManagementSystem.member_start,"Aishwarya Sharma", 197543, true);
    }
}
