# xtm-task
Simple recruitment task for XTM International made with Spring

## Run
Project was made in Maven and Boot so standard procedures apply.

## Considerations
- Further growth of applications implies creation of Exception Resolver class for streamlining error to api response processing
- `GET /client/` endpoint shoud be disabled as soon as production deployment due to privacy concerns
- `DELETE /car/{id}` endpoint missing as it was not a part of task, but further development ought to cover this
- custom errors should call `super()` for root cause support (if needed)
