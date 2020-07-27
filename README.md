# Общая информация
Данная библиотека предназначена для работы с системой онлайн-платежей для юридических и физических лиц [Pikassa.io](https://pikassa.io/).
Основной функцией данной библиотеки является передача карточных данных, используя Merchant API v.2.
***
# Основные требования
android sdk version: 21+
***
# Подключение библиотеки в проект
**Шаг 1.** Добавьте репозиторий JitPack в ваш файл сборки
Добавьте его в свой корневой build.gradle в конце репозитория:
```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
**Step 2.** Add the dependency

```gradle
dependencies {
    ...
    implementation 'com.github.pikassa-payments:pikassa-sdk-android:Tag'
}
```
***
# Пример использования
Работа с библиотекой осуществляется через объект ```Pikassa```, у которого доступны 3 метода: ```init(), sendPaymentDetails(), close() ```. Для начала необходимо вызвать метод ```init```:
```kotlin
Pikassa.init("your_api_key")
```
где ```"your_api_key"``` - ключ доступа.

После инициализации можно вызвать метод отправки карточных данных:
```kotlin
fun sendPaymentDetails(
        uuid: String,
        requestId: String,
        paymentMethod: PaymentMethod = PaymentMethod.BANK_CARD,
        details: Map<String, String>,
        onSuccess: (ResponseData) -> Unit,
        onError: (ResponseError) -> Unit
    )
```
где ```uuid``` - идентификатор счета на оплату, ```requestId``` - идентификатор заказа, ```paymentMethod``` - тип оплаты (может быть ***BankCard, WMR, YandexMoney, Mobile***), ```details``` - справочник key/value информации по карте, ```onSuccess``` - результат успешной передачи карточных данных, возвращает информацию с сервера в случае успеха (```ResponseData```), ```onError``` - ошибка при передаче данных, возвращает ошибку (```ResponseError```).
Для удобства основные поля, исползуемые в справочнике, вынесены в ```enum class DetailsFields```, его структура:
```kotlin
enum class DetailsFields(val field: String) {
    PAN("pan"),
    CARD_HOLDER("cardHolder"),
    EXP_YEAR("expYear"),
    EXP_MONTH("expMonth"),
    CVC("cvc")
}
```
Здесь:
```pan``` - номер карты (строка 16-19 знаков, указывающая идентификатор карты);
```cardHolder``` - владелец карты;
```expYear``` - год окончания срока действия карты (формат "YY");
```expMonth``` - месяц окончания срока действия карты (формат "mm");
```cvc``` - код с обратной стороны (3 цифры);

Пример создания справочника информации по карте:
```kotlin
val paymentDetails = mapOf(
            DetailsFields.PAN.field to "4111111111111111",
            DetailsFields.CARD_HOLDER.field to "ivan ivanov",
            DetailsFields.EXP_YEAR.field to "24",
            DetailsFields.EXP_MONTH.field to "12",
            DetailsFields.CVC.field to "123"
        )
```
В случае успеха выполнения отправки данных, в onSuccess приходит ответ ResponseData, структура которого выглядит следующим образом:
```kotlin
data class ResponseData(
    val uuid: String,
    val requestId: String,
    val redirect: RedirectResponse?
)
```
```uuid``` - идентификатор платежа; 
```requestId``` - идентификатор запроса
```redirect``` - ссылка редирект 3ds, структура которого содержит в себе поле ```url```, в котором хранится url редиректа;

Для завершения работы метода и в случаях когда работа метода ещё не завершена, а жизненный цикл ***Activity/Fragment*** где он вызван - уже завершен, рекомендуется вызывать метод ```close()``` в методах уничтожения экрана (```onDestroy()```, ```onDestroyView()```).
***
