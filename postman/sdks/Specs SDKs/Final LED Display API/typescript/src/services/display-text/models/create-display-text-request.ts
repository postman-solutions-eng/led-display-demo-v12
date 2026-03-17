import { z } from 'zod';

/**
 * Zod schema for the CreateDisplayTextRequest model.
 * Defines the structure and validation rules for this data type.
 * This is the shape used in application code - what developers interact with.
 */
export const createDisplayTextRequest = z.lazy(() => {
  return z.object({
    text: z.string().optional(),
  });
});

/**
 *
 * @typedef  {CreateDisplayTextRequest} createDisplayTextRequest
 * @property {string} - The text to display, optionally including icon codes in icon_name format
 */
export type CreateDisplayTextRequest = z.infer<typeof createDisplayTextRequest>;

/**
 * Zod schema for mapping API responses to the CreateDisplayTextRequest application shape.
 * Handles any property name transformations from the API schema.
 * If property names match the API schema exactly, this is identical to the application shape.
 */
export const createDisplayTextRequestResponse = z.lazy(() => {
  return z
    .object({
      text: z.string().optional(),
    })
    .transform((data) => ({
      text: data['text'],
    }));
});

/**
 * Zod schema for mapping the CreateDisplayTextRequest application shape to API requests.
 * Handles any property name transformations required by the API schema.
 * If property names match the API schema exactly, this is identical to the application shape.
 */
export const createDisplayTextRequestRequest = z.lazy(() => {
  return z
    .object({
      text: z.string().optional(),
    })
    .transform((data) => ({
      text: data['text'],
    }));
});
