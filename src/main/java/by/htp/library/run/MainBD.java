package by.htp.library.run;

public class MainBD {

	public static void main(String[] args) {
		/*
		 *
		 * Реализовать возможность хранения информации в реляционной БД (MySQL) и в
		 * памяти (используя коллекции). При проектировании слоя DAO заложить в
		 * программу необходимые интерфейсы, и предоставить 2 типа реализации: работа с
		 * БД и работа с коллекциями.
		 * 
		 * Приложение должно работать со следующей информацией (допускается расширение
		 * набора данных): список сотрудников предприятия, которые пользуются услугами
		 * библиотеки и имеют читательский билет; каталог книг, которые есть в
		 * библиотеке.
		 * 
		 * Реализуйте следующую функциональность для читателя: возможность авторизации
		 * (логин: номер читательского билета, пароль: строка не менее 6 символов и 1
		 * цифры) возможность просмотреть каталог книг (каталог может содержать
		 * несколько экземпляров одной книги); возможность просмотреть подробную
		 * информацию о конкретной книге; при авторизации в приложении читатель должен
		 * получать информационное сообщение в случае, если у него на руках имеются
		 * книги, которые он не вернул в срок (одна книга выдается на срок не более 30
		 * дней).
		 * 
		 * Реализуйте следующую функциональность для библиотекаря: возможность
		 * авторизации (логин и пароль библиотекаря заранее известны и хранятся в
		 * приложении или в БД) добавить нового читателя; добавить новую книгу;
		 * возможность вести учет выдаваемых для чтения книг (приложение должно
		 * позволить избежать ситуации, когда одному из читателей выдаётся тот экземпляр
		 * книги, который в данный момент находится на руках у другого читателя,
		 * приложение не должно позволить выдавать книгу в случае, если у читателя на
		 * руках уже находится 3 книги, либо если есть задолженность по возврату книг);
		 * возможность зафиксировать факт возврата читателем книги в библиотеку;????
		 * 
		 * Реализуйте возможность получения следующих отчётов: отчёт о читателях, у
		 * которых имеется задолженность по возврату книг (отчет содержит следующую
		 * информацию: фио читателя, номер телефона, название книги, дата выдачи,
		 * количество дней просрочки по возврату книги в библиотеку); отчёт о
		 * прочитанных книгах (отчёт содержит информацию о названии книги, количестве
		 * раз, когда данная книга выдавалась для чтения), отчет выводится в
		 * отсортированном виде начиная с самой читаемой книги.
		 *&&&  отчёт о сотрудниках, которые прочитали не менее 2-х и не более 8-ми книг за месяц.&&&&
		 * 
		 */
	}

}
