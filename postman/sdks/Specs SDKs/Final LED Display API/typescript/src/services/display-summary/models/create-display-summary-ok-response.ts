import { z } from 'zod';

/**
 * Zod schema for the CreateDisplaySummaryOkResponse model.
 * Defines the structure and validation rules for this data type.
 * This is the shape used in application code - what developers interact with.
 */
export const createDisplaySummaryOkResponse = z.lazy(() => {
  return z.object({
    status: z.string().optional(),
  });
});

/**
 *
 * @typedef  {CreateDisplaySummaryOkResponse} createDisplaySummaryOkResponse
 * @property {string} - Status of the operation
 */
export type CreateDisplaySummaryOkResponse = z.infer<typeof createDisplaySummaryOkResponse>;

/**
 * Zod schema for mapping API responses to the CreateDisplaySummaryOkResponse application shape.
 * Handles any property name transformations from the API schema.
 * If property names match the API schema exactly, this is identical to the application shape.
 */
export const createDisplaySummaryOkResponseResponse = z.lazy(() => {
  return z
    .object({
      status: z.string().optional(),
    })
    .transform((data) => ({
      status: data['status'],
    }));
});

/**
 * Zod schema for mapping the CreateDisplaySummaryOkResponse application shape to API requests.
 * Handles any property name transformations required by the API schema.
 * If property names match the API schema exactly, this is identical to the application shape.
 */
export const createDisplaySummaryOkResponseRequest = z.lazy(() => {
  return z
    .object({
      status: z.string().optional(),
    })
    .transform((data) => ({
      status: data['status'],
    }));
});
