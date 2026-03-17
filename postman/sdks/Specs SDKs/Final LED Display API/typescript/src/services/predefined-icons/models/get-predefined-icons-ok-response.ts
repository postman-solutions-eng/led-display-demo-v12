import { z } from 'zod';

/**
 * Zod schema for the GetPredefinedIconsOkResponse model.
 * Defines the structure and validation rules for this data type.
 * This is the shape used in application code - what developers interact with.
 */
export const getPredefinedIconsOkResponse = z.lazy(() => {
  return z.object({
    icons: z.array(z.string()).optional(),
  });
});

/**
 *
 * @typedef  {GetPredefinedIconsOkResponse} getPredefinedIconsOkResponse
 * @property {string[]} - Array of icon codes in :icon_name format
 */
export type GetPredefinedIconsOkResponse = z.infer<typeof getPredefinedIconsOkResponse>;

/**
 * Zod schema for mapping API responses to the GetPredefinedIconsOkResponse application shape.
 * Handles any property name transformations from the API schema.
 * If property names match the API schema exactly, this is identical to the application shape.
 */
export const getPredefinedIconsOkResponseResponse = z.lazy(() => {
  return z
    .object({
      icons: z.array(z.string()).optional(),
    })
    .transform((data) => ({
      icons: data['icons'],
    }));
});

/**
 * Zod schema for mapping the GetPredefinedIconsOkResponse application shape to API requests.
 * Handles any property name transformations required by the API schema.
 * If property names match the API schema exactly, this is identical to the application shape.
 */
export const getPredefinedIconsOkResponseRequest = z.lazy(() => {
  return z
    .object({
      icons: z.array(z.string()).optional(),
    })
    .transform((data) => ({
      icons: data['icons'],
    }));
});
