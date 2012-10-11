# Group authentication with Spring Security and CAS


Small project to test shared CAS login and logout for a group of applications.


## How to use

Just see the code and feel free to copy-paste if needed.


## How to run

* setup httpS on your servlet container
* setup CAS server
* import app01 and app02 to Eclipse and build
* write down 127.0.0.1 localhost.com to your hosts file, or change domain in both applications to suitable for you
* deploy both applications to your servlet container and run
* done

## To check single sign in

* browse unsecured pages on both applications - you are not authenticated now
* go to either safe page or login page on any application. CAS authentication process will be started.
* authenticate yourself as joe/joe
* browse any pages (including secured) on both applications - you are authenticated now.

## To check single sign out

* click Logout link
* return to any page on any application - you are not authenticated now

## How it works

1. While user browses non-protected applications pages, there is no CAS requests at all, doesn't matter if such user is authenticated or not at the moment.
2. When user authenticates by any application, this application sets domain-wide "Authenticated=true" cookie.
3. When any other application sees this cookie, it starts standard authentication process, does not matter if a protected or unprotecred page is requested.