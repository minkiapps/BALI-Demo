# Bali Demo

Demo code to show different ongoing notification types on the Huawei P50 Pocket external screen.

*Currently the external screen provides THREE different templates*

### Add a bundle to your on going notification with a Json template:

>val bundle = Bundle()<br>
>val resJson = gson.toJson(templateDemo)<br> 
>bundle.putString("res_id", resJson)<br>
>bundle.putInt("notification_index", templateIndex.ordinal) //0, 1, 2<br>
>bundle.putParcelable("extra_icon", Icon.createWithResource(this, iconRes))<br>
>. . .<br>
>notificationBuilder.setExtras(bundle)<br>


### Template 0 (Navigation Scenario):
![gif_navigation](https://user-images.githubusercontent.com/52449229/149536071-3e9dbc3f-eeaf-4a1e-a377-80f8c3082be4.gif)

```
{
  "contentColor": 0,
  "disColor": -16776961,
  "disColorContent": "Arrive",
  "secondlyColor": -1,
  "templateContent": "Arrive at Cloud Park, Longgang District, Shenzhen City, Guangdong Province",
  "titleColor": -1
}
```

![navigation](https://user-images.githubusercontent.com/52449229/149678273-a504ef66-d0fa-4f18-9777-b6285e066f30.png)

### Template 1 (Taxi Order Scenario):
![gif_taxi](https://user-images.githubusercontent.com/52449229/149534618-e1f04693-f996-4ffb-bea8-873db6bce493.gif)

```
{
  "templateBottom": {
    "disContents": [
      {
        "disContent": "2km",
        "disContentColor": -1
      },
      {
        "disContent": "2min",
        "disContentColor": -1
      }
    ],
    "oriContent": "Distance: 2km, 2min",
    "oriContentColor": -5592406
  },
  "templateContentFirst": {
    "disContents": [],
    "oriContent": "BD51-SMR",
    "oriContentColor": -1
  },
  "templateContentSecondly": {
    "disContents": [],
    "oriContent": "Audi A3 - White",
    "oriContentColor": -5592406
  },
  "templateTitle": "Driver on the way",
  "titleColor": -246245
}
```

![taxi_order](https://user-images.githubusercontent.com/52449229/149678350-4d4a9e2d-fba7-41c9-a78d-c0f46d749c0f.png)

Icon is from Freepik:
<a href="https://www.flaticon.com/free-icons/taxi" title="taxi icons">Taxi icons created by Freepik - Flaticon</a>

### Template 2 (Sport Scenario):
![gif_steps](https://user-images.githubusercontent.com/52449229/149535689-b4148569-8875-4397-bae4-73f17ca2d04e.gif)

```
{
  "bottomContent": "934m",
  "bottomContentColor": -1711276033,
  "midContent": "1.315",
  "midContentBigSize": true,
  "midContentColor": -419430401,
  "progress": 66,
  "progressBgColor": -13816018,
  "progressColor": -15292417,
  "templateTitle": "Steps",
  "titleColor": -419430401
}
```

![steps_counter](https://user-images.githubusercontent.com/52449229/149678353-d88ae076-5348-4f7a-b94c-90c6572b35c4.png)

Icon is from Freepik:
<a href="https://www.flaticon.com/free-icons/step" title="step icons">Step icons created by Freepik - Flaticon</a>

### How to make screenshot from external screen
>adb shell screencap -p -d 1 /sdcard/sc.png</br>
>adb pull /sdcard/sc.png ~/Desktop</br>

