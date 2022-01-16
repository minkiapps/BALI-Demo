# Bali Demo

Demo code to show different ongoing notification types on the Huawei P50 Pocket external screen.

*Currently the external screen provides THREE different templates,*

### Add a bundle to your on going notification with a Json template:

>val bundle = Bundle()<br>
>val resJson = gson.toJson(templateDemo)<br> 
>bundle.putString("res_id", resJson)<br>
>bundle.putInt("notification_index", templateIndex.ordinal) //0, 1, 2<br>
>bundle.putParcelable("extra_icon", Icon.createWithResource(this, iconRes))<br>
>. . .<br>
>notificationBuilder.setExtras(bundle)<br>


#### Template 0 (Navigation Scenario):
![gif_navigation](https://user-images.githubusercontent.com/52449229/149536071-3e9dbc3f-eeaf-4a1e-a377-80f8c3082be4.gif)

#### Template 1 (Taxi Order Scenario):
![gif_taxi](https://user-images.githubusercontent.com/52449229/149534618-e1f04693-f996-4ffb-bea8-873db6bce493.gif)

Icon is from Freepik:
<a href="https://www.flaticon.com/free-icons/taxi" title="taxi icons">Taxi icons created by Freepik - Flaticon</a>

#### Template 2 (Sport Scenario):
![gif_steps](https://user-images.githubusercontent.com/52449229/149535689-b4148569-8875-4397-bae4-73f17ca2d04e.gif)

Icon is from Frrepik:
<a href="https://www.flaticon.com/free-icons/step" title="step icons">Step icons created by Freepik - Flaticon</a>

### How to make screenshot from external screen
>adb shell screencap -p -d 1 /sdcard/sc.png</br>
>adb pull /sdcard/sc.png ~/Desktop</br>

