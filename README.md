# Yemek Sipariş Android Uygulaması

Yemek Sipariş Uygulaması tanımlama

Bu uygulama, Acıktım Kafe ismini verdiğim bir restoran/kafe sipariş uygulaması modelidir. Uygulama, Retrofit yardımıyla internet üzerinden JSON formatında aldığı yemek verilerini düzenleyip liste halinde kullanıcıya sunar. Kullanıcı bu listeden istediği yemekleri, sepetine ekleyip daha sonra da sipariş verebilir. Kullanıcı tüm sipariş işlemlerini giriş yaptığı hesap üzerinden vermektedir, her kullanıcı farklı cihazlardan kendi sepetine erişebilmektedir. 

## Kullanıcı Ekranları
Uygulama ilk defa açıldığında çalışan aktivitenin ekranlarıdır. Giriş, Kayıt Olma ve Şifre Sıfırlama Ekranlarından oluşmaktadır. Kullanıcıyı ilk olarak giriş ekranı karşılar. Kullanıcı bu ekrandan; kayıt olma ekranına ve şifre sıfırlama ekranına geçiş yapabilir. Kullanıcı başarılı bir şekilde giriş yaptığında, uygulama giriş sonrası aktivite ekranına geçiş yapar. Eğer kullanıcı, uygulamaya daha önce giriş yaptıysa uygulama bu aktivite sırasında bunun kontrolünü sağlar ve giriş ekranı gelmeden, giriş sonrası aktivite ekranlarına yönlendirilir. 

<a href="https://github.com/yemregul94/Android-MVVM-Food-Order-App/blob/main/screenshots/Login_Light_TR.png" target="_blank">
<img src="https://github.com/yemregul94/Android-MVVM-Food-Order-App/blob/main/screenshots/Login_Light_TR.png" width="200" style="max-width:100%;"></a>

<a href="https://github.com/yemregul94/Android-MVVM-Food-Order-App/blob/main/screenshots/register.png" target="_blank">
<img src="https://github.com/yemregul94/Android-MVVM-Food-Order-App/blob/main/screenshots/register.png" width="200" style="max-width:100%;"></a>

<a href="https://github.com/yemregul94/Android-MVVM-Food-Order-App/blob/main/screenshots/reset.png" target="_blank">
<img src="https://github.com/yemregul94/Android-MVVM-Food-Order-App/blob/main/screenshots/reset.png" width="200" style="max-width:100%;"></a>

### Kullanıcı Ekranı Hata Gösterimi
Eğer kullanıcı adı ve şifre kısımları boş ise, uygulama sunucuya istek atmadan önce kullanıcıyı uyarır. Eğer tüm alanlar dolu ise istek sunucuya iletilir ve hata durumunda gelen hata cevabı ekrana yansıtılır.

<a href="https://github.com/yemregul94/Android-MVVM-Food-Order-App/blob/main/screenshots/Login_Error_1.png" target="_blank">
<img src="https://github.com/yemregul94/Android-MVVM-Food-Order-App/blob/main/screenshots/Login_Error_1.png" width="200" style="max-width:100%;"></a>

<a href="https://github.com/yemregul94/Android-MVVM-Food-Order-App/blob/main/screenshots/Login_Error_2.png" target="_blank">
<img src="https://github.com/yemregul94/Android-MVVM-Food-Order-App/blob/main/screenshots/Login_Error_2.png" width="200" style="max-width:100%;"></a>

## Giriş Sonrası Ekranlar
Kullanıcı başarılı bir şekilde giriş yaptıktan sonra Yemek Listesi ekranı karşısına çıkar. Buradan bir yemeğe tıkladığında o yemeği içerek Yemek Detay ekranına veya Sepet ekranına geçiş yapabilir.

<a href="https://github.com/yemregul94/Android-MVVM-Food-Order-App/blob/main/screenshots/List.png" target="_blank">
<img src="https://github.com/yemregul94/Android-MVVM-Food-Order-App/blob/main/screenshots/List.png" width="200" style="max-width:100%;"></a>

### Yemek Listeleme Ekranı
Kullanıcıyı bu ekranda; bir araç çubuğu, her gün değişen günün menüsü bölmesi, önceden belirlenmiş özel seçim bölmesi, filtreleme bölmesi, yemek listesi bölmesi ve en altta da sepet bölmesi karşılar. Araç çubuğunda sıralama, arama ve hesaptan çıkış işlemleri yapılabilir. Günün menüsü, özel seçim bölmesi ve yemek listesinden bir yemeğe tıklanınca onun detayları açılır. Filtereleme butonların tıklayınca sadece o kategoriye ait yemekler ekrana getirilir. Sepet bilgilerini içerek kısma tıklandığında da kullanıcı sepetini görebilir.

<a href="https://github.com/yemregul94/Android-MVVM-Food-Order-App/blob/main/screenshots/Search.png" target="_blank">
<img src="https://github.com/yemregul94/Android-MVVM-Food-Order-App/blob/main/screenshots/Search.png" width="200" style="max-width:100%;"></a>

<a href="https://github.com/yemregul94/Android-MVVM-Food-Order-App/blob/main/screenshots/Sort.png" target="_blank">
<img src="https://github.com/yemregul94/Android-MVVM-Food-Order-App/blob/main/screenshots/Sort.png" width="200" style="max-width:100%;"></a>

<a href="https://github.com/yemregul94/Android-MVVM-Food-Order-App/blob/main/screenshots/Filter.png" target="_blank">
<img src="https://github.com/yemregul94/Android-MVVM-Food-Order-App/blob/main/screenshots/Filter.png" width="200" style="max-width:100%;"></a>

### Yemek Detayı Ekranı
Kullanıcı, yemek listesi ekranında veya sepet ekranında herhangi bir yemeğe tıkladığında, onun detaylarını içeren ekran açılır. Burada seçilen yemek miktarına göre ekran güncellenir ve sepete ekleme işlemi gerçekleştirilir. Eğer sepette aynı yemek daha önce eklendiyse, seçilen miktar toplam miktara eklenir. Kullanıcı 1-999 arasında adet seçebilir eğer daha küçük değer girmeye çalışırsa değer 1 olarak atanır, eğer daha büyük bir değer girmeye çalışırsa da değer 999 olarak atanır.

<a href="https://github.com/yemregul94/Android-MVVM-Food-Order-App/blob/main/screenshots/Details.png" target="_blank">
<img src="https://github.com/yemregul94/Android-MVVM-Food-Order-App/blob/main/screenshots/Details.png" width="200" style="max-width:100%;"></a>

### Sepet Ekranı
Kullanıcı yemek listesi ekranında veya yemek detayı ekranından sepet git butonuna tıkladığında sepet ekranı açılır. Kullanıcı isim bilgisine göre o kullanıcıya özel sepet yüklenir, kullanıcı ismine de Firebase Authentication üzerinden erişim sağlanır. Burada yemeklerin miktarlarını ve toplam fiyatlarını içeren bir liste, kullanıcıya gösterilir. Kullanıcı bu ekranda; yemekleri tek tek silme, toplu olarak silme ve siparişi tamamlama işlemleri yapabilir. Kullanıcı siparişi tamamladığında onu basit bir sonuç ekranı karşılar ve o ekranda siparişinin özet bilgisini görebilir.

<a href="https://github.com/yemregul94/Android-MVVM-Food-Order-App/blob/main/screenshots/Cart.png" target="_blank">
<img src="https://github.com/yemregul94/Android-MVVM-Food-Order-App/blob/main/screenshots/Cart.png" width="200" style="max-width:100%;"></a>

<a href="https://github.com/yemregul94/Android-MVVM-Food-Order-App/blob/main/screenshots/Result.png" target="_blank">
<img src="https://github.com/yemregul94/Android-MVVM-Food-Order-App/blob/main/screenshots/Result.png" width="200" style="max-width:100%;"></a>

## Ekranlar Arası Ortak Özellikler
Uygulamada kullanılan teknolojilere ve detaylara geçmeden önce tüm ekranlarda ortak olan 1-2 özelliğe göz atalım. Uygulama içinde kullanılan metinler strings dosyasındaki değişkenlerden atandığı için çoklu dilli kullanıma uygundur. Buna ek olarak renkler de hem gündüz hem gece modu için ayrı ayrı belirlendiği için uygulamada gece modu desteği de vardır.

<a href="https://github.com/yemregul94/Android-MVVM-Food-Order-App/blob/main/screenshots/Login_Light_TR.png" target="_blank">
<img src="https://github.com/yemregul94/Android-MVVM-Food-Order-App/blob/main/screenshots/Login_Light_TR.png" width="200" style="max-width:100%;"></a>

<a href="https://github.com/yemregul94/Android-MVVM-Food-Order-App/blob/main/screenshots/Login_Dark_EN.png" target="_blank">
<img src="https://github.com/yemregul94/Android-MVVM-Food-Order-App/blob/main/screenshots/Login_Dark_EN.png" width="200" style="max-width:100%;"></a>

## Kullanılar Teknolojiler ve Yapılar

Kotlin & Android
MVVM
Retrofit
Glide
Firebase
Coroutines
Dagger & Hilt
Data Binding
View Binding
Fragments
LiveData
