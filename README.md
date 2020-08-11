# ReactionWaiter

The ReactionWaiter library is a simple and lightweight library to improve the work with JDA and reactions.

- Current Version: 1.0.1 <br>
- JDA 4.1.1_165
- [Latest Release](https://github.com/Kaktushose/reactionwaiter/releases/tag/v.1.0.1) <br>
- [Documentation](https://kaktushose.github.io/reactionwaiter/)


# Features
- Create "ReactionWaiters" that will execute code when specific reactions are added
- easily give limitations, e.g. a specific user or message
- activate and deactivate waiters as you like
- high flexibility
- easy usage

# Add to your project

## Adding as a library
You can download the .jar-file [here](https://github.com/Kaktushose/reactionwaiter/releases/tag/v.1.0.1) and add it to your project by doing the following steps:

1. Create a new directory in your project folder
2. Copy the jar-file into it
3. Eclipse: Right-click the jar: Build Path -> Add To Build Path <br>
IntelliJ: Right-click the jar: Add As Library
4. Done. You can use it now.

## Maven
not available yet

# Getting started

### Listener registration

First of all you have to register the listener. Otherwise the library wouldn't track any events.

```java
JDA jda = yourJDABuilding();

ReactionListener.startListening(jda);
```

If wanted, you can also unregister the listener again
```java
ReactionListener.stopListening(jda);
```

### Adding a ReactionWaiter

To add a ReactionWaiter simply create a new instance using the constructor matching your needings. In this example we will listen for all users but only for one specific message. <br>
**Don't forget to call the `#startWaiting()` method!**

```java
// 1234 would be the id of the message and 👍 is the unicode for the discord Thumbs Up emoji
ReactionWaiter waiter = new ReactionWaiter(1234, "👍");

// activates the waiter
waiter.startWaiting();

// will be executed if a reaction event matches the message id and the given emoji
waiter.onEvent(reactionEvent -> {
  reactionEvent.respond("Hello World").queue();

  // deactivates the waiter again, since the reaction we have been waiting for was added
  waiter.stopWaiting();
});
```

### EmoteType

If you are too lazy to look up the unicode for an emoji you can also use the enum `EmoteType`. It covers the most useful emojis in terms of discord bots.

```java
ReactionWaiter waiter = new ReactionWaiter(1234, EmoteType.THUMBSUP.unicode);
```

That's it! We've implemented our first ReactionWaiter. Further information on how to use this library can be found in the [documentation]().

# Contributing

If you think that something is missing and you want to add it yourself, feel free to open a pull request. I recommend opening an issue first in order to avoid misunderstandings, e.g. you start working on a feature I'm already planning to add.
Please note the following things:
- Follow the Java conventions
- Document your code
- Test your code
- Your code quality should be at least as good as mine ;)
- work object-oriented and try to avoid `static`
- KISS (keep it simple and stupid)
- Keep the purpose  of this library in mind (easy and lightweight)

Thank you for reading this and hopefully using my library :)
