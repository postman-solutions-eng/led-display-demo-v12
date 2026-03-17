import { z } from 'zod';
import { ThrowableError } from '../../http/errors/throwable-error';

export type IErrorResponseSchema = {
  error?: string;
  details?: string;
};

export const errorResponseResponse = z.lazy(() => {
  return z
    .object({
      error: z.string().optional(),
      details: z.string().optional(),
    })
    .transform((data) => ({
      error: data['error'],
      details: data['details'],
    }));
});

export class ErrorResponse extends ThrowableError {
  public error?: string;
  public details?: string;
  constructor(
    public message: string,
    protected response?: unknown,
  ) {
    super(message);

    const parsedResponse = errorResponseResponse.parse(response);

    this.error = parsedResponse.error;
    this.details = parsedResponse.details;
  }

  public throw() {
    const error = new ErrorResponse(this.message, this.response);
    error.metadata = this.metadata;
    throw error;
  }
}
