In the View with the function of scrolling of the code written for Android provides convenience for the user back to the top. If you double tap the top of the Android system bar The View with the ability to scroll to the top of the scroll are. Double-touch the clock in the system bar of iPhone features that are identical to scroll to the top.

Android developers can use this functionality with a simple code.

[![Analytics](https://ga-beacon.appspot.com/UA-51734472-3/Android-TopScrollHelper/readme)](https://github.com/kmshack/Android-TopScrollHelper)

 
 

![screens](screen.png)

  
  

Auto-scroll Support View
-----------

* ScrollView
* NestedScrollView
* WebView
* ListView
* RecyclerView



  
  

Instructions
-----------

* Add the following permission to use the pop-up window on the Android Manifest.

```xml
<uses-permission android: name = "android.permission.SYSTEM_ALERT_WINDOW" />
```

  
  

* Add the view that the automatic scrolling feature scrolls to add to addTargetScrollView(view) method and removed removeTargetScrollView(view).
Activity or Fragment of a scroll view allows calls to be fit to Create and Destory LifeCycle point.



```java
@Override
protected void onCreate (Bundle savedInstanceState) {
   
   super.onCreate(savedInstanceState);
   ...
   
   mNestedScrollView = (NestedScrollView) findViewById (R.id.scrollView);
   TopScrollHelper.getInstance (getApplicationContext ()) addTargetScrollView (mNestedScrollView);
}

 

@Override
protected void onDestroy () {

   TopScrollHelper.getInstance (getApplicationContext ()) removeTargetScrollView (mNestedScrollView);
   super.onDestroy ();

}

```

* The Android 6.0 (Marshmallow) Starting obtain permission directly, and then obtained permission to call the addTargetScrollView(view).
  
  
  

Video
-----------
https://youtu.be/dlZoVnZoIVk

  
  
  

-----------
-----------
  
  
  

안드로이드의 스크롤의 기능을 가진 View에서 최상단으로 돌아가기 위한 사용자들을 편의제공을 위해 작성된 코드이다. 안드로이드 상단 시스템바에 더블탭을 하면 스크롤의 기능을 가진 View들은 최상단으로 스크롤 하게 된다. 아이폰의 시스템바의 시계를 더블터치하면 최상단으로 스크롤 되는 기능과 동일하다.

안드로이드 개발자들은 간단한 코드를 통해 이와 같은 기능을 사용할 수 있다. 
 
 

![screens](screen.png)

  
  

자동스크롤 지원 View
-----------

* ScrollView
* NestedScrollView
* WebView
* ListView
* RecyclerView



  
  

사용법
-----------

* 안드로이드 Manifest에 다음과 윈도우 팝업을 사용할 수 있도록 퍼미션을 추가 한다.

```xml
<uses-permission android: name = "android.permission.SYSTEM_ALERT_WINDOW" />
```

  
  

* 자동 스크롤 기능을 추가 하기위해 스크롤 되는 뷰를 addTargetScrollView(view)메소드로 추가하고 removeTargetScrollView(view)로 제거한다.
Activity또는 Fragment의 스크롤뷰가 Create와 Destory되는 LifeCycle 시점에 맞게 호출 해준다.




```java
@Override
protected void onCreate (Bundle savedInstanceState) {
   
   super.onCreate(savedInstanceState);
   ...
   
   mNestedScrollView = (NestedScrollView) findViewById (R.id.scrollView);
   TopScrollHelper.getInstance (getApplicationContext ()) addTargetScrollView (mNestedScrollView);
}

 

@Override
protected void onDestroy () {

   TopScrollHelper.getInstance (getApplicationContext ()) removeTargetScrollView (mNestedScrollView);
   super.onDestroy ();

}
```


* 안드로이드 6.0(마시멜로)부터는 직접 퍼미션을 얻어야 하며, 퍼미션을 얻은 후 addTargetScrollView(view)를 호출한다.
  
  
  

영상
-----------
https://youtu.be/dlZoVnZoIVk






