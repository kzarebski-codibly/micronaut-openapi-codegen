package codegen;

import java.util.List;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.exceptions.HttpStatusException;
import io.reactivex.Single;

@Controller
class ResponseController implements ResponseApi {

	static final Double DOUBLE = 1234D;
	static final String STRING = "string";
	static final List<String> ARRAY = List.of("a", "b");

	@Override
	public Single<HttpResponse<List<String>>> array() {
		return Single.just(HttpResponse.ok(ARRAY));
	}

	@Override
	public Single<HttpResponse<?>> header() {
		return Single.just(HttpResponse.ok());
	}

	@Override
	public Single<HttpResponse<List<String>>> arrayNotFound(Boolean found) {
		return Single.just(found ? HttpResponse.ok(ARRAY) : HttpResponse.notFound());
	}

	@Override
	public Single<HttpResponse<String>> multiple(Boolean redirect) {
		if (redirect) {
			throw new HttpStatusException(HttpStatus.MULTIPLE_CHOICES, DOUBLE);
		}
		return Single.just(HttpResponse.ok(STRING));
	}

	@Override
	public Single<HttpResponse<String>> multipleNotFound(Boolean redirect, Boolean found) {
		if (redirect) {
			throw new HttpStatusException(HttpStatus.MULTIPLE_CHOICES, DOUBLE);
		}
		return Single.just(found ? HttpResponse.ok(STRING) : HttpResponse.notFound());
	}

	@Override
	public Single<HttpResponse<String>> optional(Boolean found) {
		return Single.just(found ? HttpResponse.ok(STRING) : HttpResponse.notFound());
	}

	@Override
	public Single<HttpResponse<String>> single() {
		return Single.just(HttpResponse.ok(STRING));
	}

	@Override
	public Single<HttpResponse<?>> voidNormal() {
		return Single.just(HttpResponse.noContent());
	}

	@Override
	public Single<HttpResponse<?>> voidNotFound(Boolean found) {
		return Single.just(found ? HttpResponse.noContent() : HttpResponse.notFound());
	}
}
