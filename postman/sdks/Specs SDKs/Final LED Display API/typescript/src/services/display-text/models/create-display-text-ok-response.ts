import { z } from 'zod';

/**
 * Zod schema for the CreateDisplayTextOkResponse model.
 * Defines the structure and validation rules for this data type.
 * This is the shape used in application code - what developers interact with.
 */
export const createDisplayTextOkResponse = z.lazy(() => {
  return z.object({
    status: z.string().optional(),
    text: z.string().optional(),
  });
});

/**
 *
 * @typedef  {CreateDisplayTextOkResponse} createDisplayTextOkResponse
 * @property {string}
 * @property {string}
 */
export type CreateDisplayTextOkResponse = z.infer<typeof createDisplayTextOkResponse>;

/**
 * Zod schema for mapping API responses to the CreateDisplayTextOkResponse application shape.
 * Handles any property name transformations from the API schema.
 * If property names match the API schema exactly, this is identical to the application shape.
 */
export const createDisplayTextOkResponseResponse = z.lazy(() => {
  return z
    .object({
      status: z.string().optional(),
      text: z.string().optional(),
    })
    .transform((data) => ({
      status: data['status'],
      text: data['text'],
    }));
});

/**
 * Zod schema for mapping the CreateDisplayTextOkResponse application shape to API requests.
 * Handles any property name transformations required by the API schema.
 * If property names match the API schema exactly, this is identical to the application shape.
 */
export const createDisplayTextOkResponseRequest = z.lazy(() => {
  return z
    .object({
      status: z.string().optional(),
      text: z.string().optional(),
    })
    .transform((data) => ({
      status: data['status'],
      text: data['text'],
    }));
});
