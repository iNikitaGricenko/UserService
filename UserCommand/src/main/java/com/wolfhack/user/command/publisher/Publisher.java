package com.wolfhack.user.command.publisher;

import com.wolfhack.user.command.model.event.Event;

import java.util.List;

public interface Publisher<I extends Event> {

	void publish(I event);

	void publish(List<I> event);

	void fail(I event);

	void fail(List<I> event);

}
