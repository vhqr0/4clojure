.PHONY: compile
compile: chat-compile todo-compile

.PHONY: build
build: chat-build todo-build

.PHONY: clean
clean: chat-clean todo-clean



.PHONY: chat-compile
chat-compile:
	shadow-cljs compile chat

.PHONY: chat-build
chat-build:
	shadow-cljs release chat

.PHONY: chat-clean
chat-clean:
	rm -rf public/js/chat



.PHONY: todo-compile
todo-compile:
	shadow-cljs compile todo

.PHONY: todo-build
todo-build:
	shadow-cljs release todo

.PHONY: todo-clean
todo-clean:
	rm -rf public/js/todo
