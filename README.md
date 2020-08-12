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
**Шаг 2.** Добавьте зависимость

```gradle
dependencies {
    ...
    implementation 'com.github.pikassa-payments:pikassa-sdk-android:Tag'
}
```
***
# Пример использования
Работа с библиотекой осуществляется через объект ```Pikassa```, у которого доступны 3 метода: ```init(), sendCardData(), close() ```. Для начала необходимо вызвать метод ```init```:
```kotlin
Pikassa.init("your_api_key")
```
где ```"your_api_key"``` - ключ доступа.

После инициализации можно вызвать метод получения карточных данных:
```kotlin
fun sendCardData(
        uuid: String,
        requestId: String,
        paymentMethod: PaymentMethod,
        onSuccess: (ResponseData) -> Unit,
        onError: (ResponseError) -> Unit
    )
```
где ```uuid``` - идентификатор счета на оплату, ```requestId``` - идентификатор заказа, ```paymentMethod``` - тип оплаты (может быть ***BankCard, Custom***, описание ниже), ```onSuccess``` - результат успешной передачи карточных данных, возвращает информацию с сервера в случае успеха (```ResponseData```), ```onError``` - ошибка при передаче данных, возвращает ошибку (```ResponseError```).

Структура ```PaymentMethod```:
```kotlin
sealed class PaymentMethod {
    data class BankCard(
        val pan: String,
        val cardHolder: String,
        val expYear: String,
        val expMonth: String,
        val cvc: String,
        val someParam: Map<String, Any>?
    ) : PaymentMethod()

    data class Custom(
        val data: Map<String, String>
    ) : PaymentMethod()
}
```
Описание  ```PaymentMethod.BankCard```:
```pan``` - номер карты (строка 16 знаков, указывающие идентификатор карты);
```cardHolder``` - владелец карты;
```expYear``` - год окончания срока действия карты (формат "YY");
```expMonth``` - месяц окончания срока действия карты (формат "mm");
```cvc``` - код с обратной стороны (3 цифры);

Пример:
```kotlin
PaymentMethod.BankCard(
    pan = "4111111111111111",
    cardHolder = "ivan ivanov",
    expYear = "24",
    expMonth = "12",
    cvc = "123"
)
```


Описание  ```PaymentMethod.Custom```:
```data``` - тип данных Map (словарь) строка - строка;

Пример:
```kotlin
PaymentMethod.Custom(
    data = mapOf(
               "paymentMethod" to "Mobile",
               "phone" to "+79999999999" 
            )
)
```

Для завершения работы метода и в случаях когда работа метода ещё не завершена, а жизненный цикл ***Activity/Fragment*** где он вызван - уже завершен, рекомендуется вызывать метод ```close()``` в методах уничтожения экрана (```onDestroy()```, ```onDestroyView()```).
***